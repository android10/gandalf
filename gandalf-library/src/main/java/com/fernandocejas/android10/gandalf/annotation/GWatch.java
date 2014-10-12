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
 * <br>Gandalf watching aspect that indicates the annotated method/constructor will collect
 * stats like:<br>
 *
 * <br>- Number of times executed.
 * <br>- Total execution time accumulated.
 *
 * <br><br>TO REMEMBER:<br>
 * <br>To see the stats, at any point, you just call
 * {@link com.fernandocejas.android10.gandalf.Gandalf#printStats()}, thus, the information will be
 * printed and the results will be shown in the logcat only in debug mode using
 * {@link com.fernandocejas.android10.gandalf.internal.DebugLog}.<br>
 *
 * <br>OUTPUT EXAMPLE:<br>
 * <br>Gandalf => [@Method -> yourMethodOne() :: Executed 4 times :: Total time 100 ms]
 * <br>Gandalf => [@Method -> yourMethodTwo() :: Executed 2 times :: Total time 36 ms]<br>
 *
 * <br>------------------------------------------------------------------------------------------
 * <br>"Look to my coming on the first light of the fifth day, at dawn look to the east." -Gandalf
 * <br>------------------------------------------------------------------------------------------
 */
@Retention(CLASS)
@Target({CONSTRUCTOR, METHOD })
public @interface GWatch {}
