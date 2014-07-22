/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package org.android10.gandalf.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;

/**
 * Indicates that the annotated method is being traced (debug mode only) and
 * will use Android Log to print debug data:
 * - Method name
 * - Method parameters
 * - Total execution time
 */
@Retention(RetentionPolicy.SOURCE)
@Target({CONSTRUCTOR, METHOD})
public @interface DebugTrace {}
