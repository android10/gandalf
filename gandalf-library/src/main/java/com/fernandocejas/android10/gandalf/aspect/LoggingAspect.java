/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.aspect;

import com.fernandocejas.android10.gandalf.annotation.GLog;
import com.fernandocejas.android10.gandalf.internal.MessageManager;
import com.fernandocejas.android10.gandalf.internal.StopWatch;
import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPoint;
import java.lang.annotation.Annotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect representing the cross cutting-concern: Method and Constructor Logging.
 */
@Aspect
public class LoggingAspect {

  private static final String POINTCUT_METHOD =
      "execution(@com.fernandocejas.android10.gandalf.annotation.GLog * *(..))";

  private static final String POINTCUT_CONSTRUCTOR =
      "execution(@com.fernandocejas.android10.gandalf.annotation.GLog *.new(..))";

  private final MessageManager messageManager;

  public LoggingAspect() {
    this(new MessageManager());
  }

  public LoggingAspect(MessageManager messageManager) {
    this.messageManager = new MessageManager();
  }

  @Pointcut(POINTCUT_METHOD)
  public void methodAnnotatedWithGLog() {}

  @Pointcut(POINTCUT_CONSTRUCTOR)
  public void constructorAnnotatedWithGLog() {}

  @Around("methodAnnotatedWithGLog() || constructorAnnotatedWithGLog()")
  public Object weaveAroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

    GandalfJoinPoint gandalfJoinPoint = new GandalfJoinPoint(joinPoint);

    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object result = joinPoint.proceed();
    stopWatch.stop();

    Annotation annotation = gandalfJoinPoint.getAnnotation(GLog.class);
    if (annotation != null) {
      switch (((GLog) annotation).value()) {
        case SIGNATURE:
          this.messageManager.printLoggingAspectMessageSignature(gandalfJoinPoint, result);
          break;
        case THREAD:
          this.messageManager.printLoggingAspectMessageThread(gandalfJoinPoint);
          break;
        case TIME:
          this.messageManager.printLoggingAspectMessageTime(gandalfJoinPoint,
              String.valueOf(stopWatch.getTotalTimeMillis()));
          break;
        case EVERYTHING:
        default:
          this.messageManager.printLoggingAspectMessageEverything(gandalfJoinPoint, result,
              String.valueOf(stopWatch.getTotalTimeMillis()));
          break;
      }
    } else {
      this.messageManager.printLoggingAspectMessageEverything(gandalfJoinPoint, result,
          String.valueOf(stopWatch.getTotalTimeMillis()));
    }

    return result;
  }
}
