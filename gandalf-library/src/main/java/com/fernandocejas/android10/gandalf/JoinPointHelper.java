/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf;

import android.os.Looper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * {@link JoinPoint} helper.
 */
public class JoinPointHelper {

  /**
   * Check if the {@link JoinPoint} is being executed in the main thread.
   *
   * @return true: main thread, otherwise false.
   */
  public static boolean isMainThread() {
    return (Looper.getMainLooper() == Looper.myLooper());
  }

  /**
   * Check if the {@link JoinPoint} has a return type.
   *
   * @param joinPoint the {@link JoinPoint} to check.
   * @return true if there is a return type, false if it is void.
   */
  public static boolean hasReturnType(JoinPoint joinPoint) {
    Signature signature = joinPoint.getSignature();

    return (signature instanceof MethodSignature
        && ((MethodSignature) signature).getReturnType() != void.class);
  }
}
