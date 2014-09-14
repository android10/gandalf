/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.aspect;

import com.fernandocejas.android10.gandalf.MessageBuilder;
import com.fernandocejas.android10.gandalf.internal.DebugLog;
import com.fernandocejas.android10.gandalf.internal.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Aspect representing the cross cutting-concern: Method Tracing.
 */
@Aspect
public class TracingAspect {

  private static final String POINTCUT_METHOD =
      "execution(@com.fernandocejas.android10.gandalf.annotation.Traceable * *(..))";

  private static final String POINTCUT_CONSTRUCTOR =
      "execution(@com.fernandocejas.android10.gandalf.annotation.Traceable *.new(..))";

  @Pointcut(POINTCUT_METHOD)
  public void methodAnnotatedWithTraceable() {}

  @Pointcut(POINTCUT_CONSTRUCTOR)
  public void constructorAnnotatedWithTraceable() {}

  @Around("methodAnnotatedWithTraceable() || constructorAnnotatedWithTraceable()")
  public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

    //Get method information
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    String className = methodSignature.getDeclaringType().getSimpleName();
    String methodName = methodSignature.getName();
    String[] methodParams = methodSignature.getParameterNames();
    Object[] methodParamValues = joinPoint.getArgs();
    String threadName = Thread.currentThread().getName();

    //Build enter message
    String enterMessage =
        buildEnterMessage(threadName, methodName, methodParams, methodParamValues);
    DebugLog.log(className, enterMessage);

    //We start to count and execute method
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object result = joinPoint.proceed();
    stopWatch.stop();

    //Build exit message
    String returnValue = (result != null) ? String.valueOf(result) : null;
    String exitMessage =
        buildExitMessage(methodName, returnValue, String.valueOf(stopWatch.getTotalTimeMillis()));
    DebugLog.log(className, exitMessage);

    return result;
  }

  private static String buildEnterMessage(String threadName, String methodName,
      String[] methodParams, Object[] methodParamValues) {
    return MessageBuilder.buildTraceEnterMessage(threadName, methodName, methodParams,
        methodParamValues);
  }

  private static String buildExitMessage(String methodName, String returnValue, String timeMillis) {
    return MessageBuilder.buildTraceExitMessage(methodName, returnValue, timeMillis);
  }
}
