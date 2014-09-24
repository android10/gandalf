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
 * <br>Indicates that the annotated method will collect stats like:<br>
 *
 * <br>- Number of times executed.
 * <br>- Total execution time accumulated.
 *
 * <br><br>TO REMEMBER:<br>
 * <br>To see the stats, at any point, you just call
 * {@link com.fernandocejas.android10.gandalf.Gandalf#printStats()}, thus, the information will be
 * printed and the results will be shown in the logcat only in debug mode using
 * {@link com.fernandocejas.android10.gandalf.internal.DebugLog}.
 */
@Retention(CLASS)
@Target({CONSTRUCTOR, METHOD })
public @interface Trackable {}
