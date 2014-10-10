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
 * <br>Gandalf Strict Mode enables Android {@link android.os.StrictMode} on your application, so
 * when violations are detected, the logcat output will show information about these ones as they
 * happen.<br>
 *
 * <br>REMEMBER: It is not necessary to annotate each method/constructor, it is enough with
 * just using the annotation on the main entry point of your app to activate
 * {@link android.os.StrictMode}. Thus this feature will live along the execution of your app.<br>
 *
 * <br>FOR EXAMPLE: It is a good practice to annotate
 * {@link android.app.Activity#onCreate(android.os.Bundle)} method of your main activity or
 * {@link android.app.Application#onCreate()} method of your {@link android.app.Application} class
 * so the violations can be detected as they happen along the execution of your app.
 */
@Retention(CLASS)
@Target({ CONSTRUCTOR, METHOD })
public @interface GStrictMode {}
