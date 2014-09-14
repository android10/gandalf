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
 * Indicates that the annotated method is being traced and will use {@link android.util.Log}
 * to print debug data:
 *
 * - Method name
 * - Total execution time
 * - Value (optional string parameter)
 */
@Retention(CLASS)
@Target({ CONSTRUCTOR, METHOD })
public @interface TraceMode {}
