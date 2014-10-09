/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.aspect;

import android.os.StrictMode;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class StrictModeAspect {

  private static final String POINTCUT_METHOD =
      "execution(@com.fernandocejas.android10.gandalf.annotation.StrictModeDebug * *(..))";

  private static final String POINTCUT_CONSTRUCTOR =
      "execution(@com.fernandocejas.android10.gandalf.annotation.StrictModeDebug *.new(..))";

  @Pointcut(POINTCUT_METHOD)
  public void methodAnnotatedWithStrictModeDebug() {}

  @Pointcut(POINTCUT_CONSTRUCTOR)
  public void constructorAnnotatedWithStrictModeDebug() {}

  @Before("methodAnnotatedWithStrictModeDebug() || constructorAnnotatedWithStrictModeDebug()")
  public void weaveBeforeJoinPoint() throws Throwable {
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectAll()
        .penaltyLog()
        .build());
    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
        .detectAll()
        .penaltyLog()
        .penaltyDeath()
        .build());
  }
}
