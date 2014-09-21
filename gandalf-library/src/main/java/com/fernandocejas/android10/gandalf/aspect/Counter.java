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
@DeclarePrecedence("com.fernandocejas.android10.gandalf.aspect.CountingAspect")
public class Counter {

  private static final String POINTCUT_METHOD =
      "execution(@com.fernandocejas.android10.gandalf.annotation.Countable * *(..))";

  private static final String POINTCUT_CONSTRUCTOR =
      "execution(@com.fernandocejas.android10.gandalf.annotation.Countable *.new(..))";

  @Pointcut(POINTCUT_METHOD)
  public void methodAnnotatedWithCountable() {}

  @Pointcut(POINTCUT_CONSTRUCTOR)
  public void constructorAnnotatedWithCountable() {}

  @After("methodAnnotatedWithCountable() || constructorAnnotatedWithCountable()")
  public void weaveAfterJoinPoint(JoinPoint joinPoint) {
    DebugLog.log("Counter---->", "Penano----> Counter executed");
  }
}
