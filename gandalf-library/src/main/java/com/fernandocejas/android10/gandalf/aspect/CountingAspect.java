/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.aspect;

import com.fernandocejas.android10.gandalf.internal.DebugLog;
import com.fernandocejas.android10.gandalf.internal.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Aspect representing the cross cutting-concern: Method and Constructor Time Counting.
 */
@Aspect
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
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    String className = methodSignature.getDeclaringType().getSimpleName();
    String methodName = methodSignature.getName();

    timesExecuted++;
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object result = joinPoint.proceed();
    stopWatch.stop();
    totalExecutionTime += stopWatch.getTotalTimeMillis();

    return result;
  }

  @After("methodAnnotatedWithCountable() || constructorAnnotatedWithCountable()")
  public void weaveAfterJoinPoint(JoinPoint joinPoint) {
    DebugLog.log("Penano---->", "Penano----> " + timesExecuted);
    DebugLog.log("Penano---->", "Penano----> " + totalExecutionTime + " ms");
  }
}
