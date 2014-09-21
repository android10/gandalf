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
 * Simple tracing aspect annotation that prints a debug message at both entering and exiting method,
 * plus method parameter and execution thread. Useful to increase the visibility of the internal
 * workings of a program.
 *
 * Example of what will be shown in the logcat (only debug version type of the app):
 *
 * Gandalf => [Entering :: @Method -> yourMethod(param1="Tony", param2="Stark") :: @Thread -> MainThread]
 * Gandalf => [Exiting :: @Method -> yourMethod() :: @Time -> 5 ms]
 */
@Retention(CLASS)
@Target({ CONSTRUCTOR, METHOD})
public @interface Traceable {}
