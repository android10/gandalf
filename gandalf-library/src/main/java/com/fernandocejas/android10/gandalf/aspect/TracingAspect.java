/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.aspect;

import com.fernandocejas.android10.gandalf.internal.MessageManager;
import com.fernandocejas.android10.gandalf.internal.StopWatch;
import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect representing the cross cutting-concern: Method Tracing.
 */
@Aspect
public class TracingAspect {

  private static final String POINTCUT_METHOD =
      "execution(@com.fernandocejas.android10.gandalf.annotation.Traceable * *(..))";

  private static final String POINTCUT_CONSTRUCTOR =
      "execution(@com.fernandocejas.android10.gandalf.annotation.Traceable *.new(..))";

  private final MessageManager messageManager;

  public TracingAspect() {
    this(new MessageManager());
  }

  public TracingAspect(MessageManager messageManager) {
    this.messageManager = new MessageManager();
  }

  @Pointcut(POINTCUT_METHOD)
  public void methodAnnotatedWithTraceable() {}

  @Pointcut(POINTCUT_CONSTRUCTOR)
  public void constructorAnnotatedWithTraceable() {}

  @Around("methodAnnotatedWithTraceable() || constructorAnnotatedWithTraceable()")
  public Object weaveAroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

    GandalfJoinPoint gandalfJoinPoint = new GandalfJoinPoint(joinPoint);
    this.messageManager.printTracingAspectEnterMessage(gandalfJoinPoint);

    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object result = joinPoint.proceed();
    stopWatch.stop();

    this.messageManager.printTracingAspectExitMessage(gandalfJoinPoint, result,
        String.valueOf(stopWatch.getTotalTimeMillis()));

    return result;
  }
}
