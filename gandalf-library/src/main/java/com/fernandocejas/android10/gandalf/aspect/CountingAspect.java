/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.aspect;

import com.fernandocejas.android10.gandalf.internal.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclarePrecedence;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect representing the cross cutting-concern: Method and Constructor Time Counting.
 */
@Aspect
@DeclarePrecedence(
    "com.fernandocejas.android10.gandalf.aspect.CountingAspect, com.fernandocejas.android10.gandalf.aspect.Counter")
public class CountingAspect {

  private static final String POINTCUT_METHOD =
      "execution(@com.fernandocejas.android10.gandalf.annotation.Countable * *(..))";

  private static final String POINTCUT_CONSTRUCTOR =
      "execution(@com.fernandocejas.android10.gandalf.annotation.Countable *.new(..))";

  private int timesExecuted = 0;
  private long totalExecutionTime = 0;

  @Pointcut(POINTCUT_METHOD)
  public void methodAnnotatedWithCountable() {}

  @Pointcut(POINTCUT_CONSTRUCTOR)
  public void constructorAnnotatedWithCountable() {}

  @Around("methodAnnotatedWithCountable() || constructorAnnotatedWithCountable()")
  public Object weaveAroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object result = joinPoint.proceed();
    stopWatch.stop();
    timesExecuted++;
    totalExecutionTime += stopWatch.getTotalTimeMillis();

    return result;
  }

  public long getTotalExecutionTime() {
    return this.totalExecutionTime;
  }

  public int getTimesExecuted() {
    return this.timesExecuted;
  }
}
