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
 * <br>Gandalf tracing aspect annotation that prints a debug message at both when entering and
 * exiting from a method/constructor. It also adds method parameters (with values) and execution
 * thread. Useful to increase the visibility of the internal workings of a program.<br>
 *
 * <br>OUTPUT EXAMPLE:<br>
 *
 * <br>Gandalf => [Entering :: @Method -> yourMethod(param1="Tony", param2="Stark") :: @Thread -> MainThread]<br>
 * <br>Gandalf => [Exiting :: @Method -> yourMethod() :: @Time -> 5 ms]<br>
 *
 * <br>------------------------------------------------------------------------------------------
 * <br>"Be on your guard. There are older and fouler things than Orcs in the deep places of the
 * world." -Gandalf
 * <br>------------------------------------------------------------------------------------------
 */
@Retention(CLASS)
@Target({ CONSTRUCTOR, METHOD})
public @interface Traceable {}
