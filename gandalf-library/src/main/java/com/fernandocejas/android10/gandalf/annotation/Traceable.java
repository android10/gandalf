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
 * <br>Simple tracing aspect annotation that prints a debug message at both entering and exiting
 * method, plus method parameter and execution thread. Useful to increase the visibility of the
 * internal workings of a program.<br>
 *
 * <br>Example of what will be shown in the logcat when using this annotation
 * (only debug version type of the app):<br>
 *
 * <br>Gandalf => [Entering :: @Method -> yourMethod(param1="Tony", param2="Stark") :: @Thread -> MainThread]<br>
 * <br>Gandalf => [Exiting :: @Method -> yourMethod() :: @Time -> 5 ms]<br>
 */
@Retention(CLASS)
@Target({ CONSTRUCTOR, METHOD})
public @interface Traceable {}
