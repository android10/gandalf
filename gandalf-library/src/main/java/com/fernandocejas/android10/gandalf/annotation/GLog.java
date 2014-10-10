/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.fernandocejas.android10.gandalf.annotation.GLog.LogScope.EVERYTHING;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <br>Gandalf logging aspect that indicates that the annotated method/constructor will print the
 * following information on the android logcat:<br>
 *
 * <br>- Method signature with parameter values.
 * <br>- Current execution thread.
 * <br>- Total execution time.
 *
 * <br><br>A {@link LogScope} option can be passed to choose different logging scopes.
 *
 * <br>Example of the output logging everything using {@link LogScope#EVERYTHING}
 * will be:<br>
 *
 * <br>Gandalf => [@Method -> yourMethod(param1="Tony", param2="Stark") :: @Thread -> MainThread :: @Time -> 5 ms]<br>
 *
 * <br>------------------------------------------------------------------------------------------
 * <br>"The Ring has awoken, it’s heard its masters call." -Gandalf The White
 * <br>------------------------------------------------------------------------------------------
 */
@Retention(RUNTIME)
@Target({ CONSTRUCTOR, METHOD })
public @interface GLog {
  public LogScope value() default EVERYTHING;

  /**
   * Logging scope of the current method/constructor annotated.
   */
  public enum LogScope {
    /** Will log method signature with parameter values, execution time and execution thread. */
    EVERYTHING,

    /** Will log method signature with parameter values. */
    SIGNATURE,

    /** Will log method name and current execution thread name. */
    THREAD,

    /** Will log method name and execution time. */
    TIME
  }
}
