/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.fernandocejas.android10.gandalf.annotation.Loggable.LoggingLevel.EVERYTHING;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Simple logging aspect that indicates that the annotated method is being logged (application debug
 * type only) and will print:
 *
 * - Method signature with parameter values.
 * - Current execution thread.
 * - Total execution time.
 *
 * A {@link LoggingLevel} option can be passed to choose different logging level information.
 *
 * Example of the output logging everything using {@link LoggingLevel#EVERYTHING default option}
 * will be:
 *
 * Gandalf => [@Method -> yourMethod(param1="Tony", param2="Stark") :: @Thread -> MainThread :: @Time -> 5 ms]
 */
@Retention(RUNTIME)
@Target({ CONSTRUCTOR, METHOD })
public @interface Loggable {
  public LoggingLevel value() default EVERYTHING;

  /**
   * Logging scope of the current method/constructor annotated.
   */
  public enum LoggingLevel {
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
