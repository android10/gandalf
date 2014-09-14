/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.aspect;

import com.fernandocejas.android10.gandalf.internal.DebugLog;
import com.fernandocejas.android10.gandalf.internal.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Aspect representing the cross cutting-concern: Method and Constructor Logging.
 */
@Aspect
public class LoggingAspect {

  private static final String POINTCUT_METHOD =
      "execution(@com.fernandocejas.android10.gandalf.annotation.Loggable * *(..))";

  private static final String POINTCUT_CONSTRUCTOR =
      "execution(@com.fernandocejas.android10.gandalf.annotation.Loggable *.new(..))";

  @Pointcut(POINTCUT_METHOD)
  public void methodAnnotatedWithLoggable() {}

  @Pointcut(POINTCUT_CONSTRUCTOR)
  public void constructorAnnotatedWithLoggable() {}

  @Around("methodAnnotatedWithLoggable() || constructorAnnotatedWithLoggable()")
  public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    String className = methodSignature.getDeclaringType().getSimpleName();
    String methodName = methodSignature.getName();

    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object result = joinPoint.proceed();
    stopWatch.stop();

    DebugLog.log(className, buildLogMessage(methodName, stopWatch.getTotalTimeMillis()));

    return result;
  }

  /**
   * Create a log message.
   *
   * @param methodName A string with the method name.
   * @param methodDuration Duration of the method in milliseconds.
   * @return A string representing message.
   */
  private static String buildLogMessage(String methodName, long methodDuration) {
    StringBuilder message = new StringBuilder();
    message.append("Gandalf --> ");
    message.append(methodName);
    message.append(" --> ");
    message.append("[");
    message.append(methodDuration);
    message.append("ms");
    message.append("]");

    return message.toString();
  }
}
