/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.fernandocejas.android10.gandalf.annotation.Loggable.LoggingLevel.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Indicates that the annotated method is being traced and will use {@link android.util.Log}
 * to print debug data:
 *
 * - Method signature with parameter values.
 * - Total execution time.
 *
 * A {@link LoggingLevel} option can be passed to choose the level of logging.
 */
@Retention(CLASS)
@Target({ CONSTRUCTOR, METHOD })
public @interface Loggable {
  public LoggingLevel value() default EVERYTHING;

  /**
   * Logging scope of the current method/constructor.
   */
  public enum LoggingLevel {
    /** Will log method signature with parameter values and execution time. */
    EVERYTHING,

    /** Will log method signature with parameter values. */
    SIGNATURE,

    /** Will log method execution time. */
    TIME
  }
}
