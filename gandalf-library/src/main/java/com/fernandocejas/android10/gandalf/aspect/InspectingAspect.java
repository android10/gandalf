/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.aspect;

import com.fernandocejas.android10.gandalf.internal.DebugLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclarePrecedence;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@DeclarePrecedence("com.fernandocejas.android10.gandalf.aspect.TrackingAspect")
public class InspectingAspect {

  private static final String POINTCUT_APPLICATION_CREATE =
      "execution(void *android.app.Application+.*onCreate(..))";

  private static final String POINTCUT_APPLICATION_DESTROY =
      "execution(void *android.app.Application+.*onTerminate(..))";

  private static final String POINTCUT_ACTIVITY_CREATE =
      "execution(void *android.app.Activity+.*onCreate(..))";

  private static final String POINTCUT_ACTIVITY_DESTROY =
      "execution(void *android.app.Activity+.*onDestroy(..))";

  public InspectingAspect() {}

  @Pointcut(POINTCUT_APPLICATION_CREATE)
  public void onApplicationCreated() {}

  @Pointcut(POINTCUT_APPLICATION_DESTROY)
  public void onApplicationDestroyed() {}

  @After("onApplicationCreated()")
  public void weaveAfterApplicationCreated(JoinPoint joinPoint) {
    new DebugLog().log("Stats---->", "Penano----> onApplicationCreated!!!");
  }

  @After("onApplicationDestroyed()")
  public void weaveAfterApplicationDestroyed(JoinPoint joinPoint) {
    new DebugLog().log("Stats---->", "Penano----> onApplicationTerminated!!!");
  }
}
