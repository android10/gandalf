/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.aspect;

import com.fernandocejas.android10.gandalf.internal.StopWatch;
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
public class TrackingAspect {

  private static final String POINTCUT_METHOD =
      "execution(@com.fernandocejas.android10.gandalf.annotation.Trackable * *(..))";

  private static final String POINTCUT_CONSTRUCTOR =
      "execution(@com.fernandocejas.android10.gandalf.annotation.Trackable *.new(..))";

  private Map<String, String> statsMap = new ConcurrentHashMap<String, String>();

  private int timesExecuted = 0;
  private long totalExecutionTime = 0;

  @Pointcut(POINTCUT_METHOD)
  public void methodAnnotatedWithTrackable() {}

  @Pointcut(POINTCUT_CONSTRUCTOR)
  public void constructorAnnotatedWithTrackable() {}

  @Around("methodAnnotatedWithTrackable() || constructorAnnotatedWithTrackable()")
  public Object weaveAroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object result = joinPoint.proceed();
    stopWatch.stop();
    timesExecuted++;
    totalExecutionTime += stopWatch.getTotalTimeMillis();

    return result;
  }
}
