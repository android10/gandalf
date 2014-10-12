/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.aspect;

import com.fernandocejas.android10.gandalf.internal.StopWatch;
import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPoint;
import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPointStats;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect representing the cross cutting-concern: Stats tracking.
 */
@Aspect
public class WatchingAspect {

  private static final String POINTCUT_METHOD =
      "execution(@com.fernandocejas.android10.gandalf.annotation.GWatch * *(..))";

  private static final String POINTCUT_CONSTRUCTOR =
      "execution(@com.fernandocejas.android10.gandalf.annotation.GWatch *.new(..))";

  private final Map<GandalfJoinPoint, GandalfJoinPointStats> statsMap =
      new ConcurrentHashMap<GandalfJoinPoint, GandalfJoinPointStats>();

  @Pointcut(POINTCUT_METHOD)
  public void methodAnnotatedWithGWatch() {}

  @Pointcut(POINTCUT_CONSTRUCTOR)
  public void constructorAnnotatedWithGWatch() {}

  @Around("methodAnnotatedWithGWatch() || constructorAnnotatedWithGWatch()")
  public Object weaveAroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object result = joinPoint.proceed();
    stopWatch.stop();

    GandalfJoinPoint gandalfJoinPoint = new GandalfJoinPoint(joinPoint);
    GandalfJoinPointStats gandalfJoinPointStats;
    if (this.statsMap.containsKey(gandalfJoinPoint)) {
      gandalfJoinPointStats = this.statsMap.get(gandalfJoinPoint);
    } else {
      gandalfJoinPointStats = new GandalfJoinPointStats(gandalfJoinPoint);
      this.statsMap.put(gandalfJoinPoint, gandalfJoinPointStats);
    }
    gandalfJoinPointStats.incrementTimesExecuted();
    gandalfJoinPointStats.accumulateExecutionTime(stopWatch.getTotalTimeMillis());

    return result;
  }

  public Map<GandalfJoinPoint, GandalfJoinPointStats> getStatsMap() {
    return this.statsMap;
  }
}
