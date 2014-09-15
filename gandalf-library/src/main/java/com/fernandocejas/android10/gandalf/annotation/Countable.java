/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Indicates that the annotated method will count:
 *
 * - Number of times it was executed.
 * - Total execution time accumulated.
 *
 * The information will be printed in debug mode using
 * {@link com.fernandocejas.android10.gandalf.internal.DebugLog}
 */
@Retention(CLASS)
@Target({ CONSTRUCTOR, METHOD })
public @interface Countable {}
