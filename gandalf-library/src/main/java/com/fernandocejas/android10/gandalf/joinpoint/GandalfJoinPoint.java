/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.joinpoint;

import android.os.Looper;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Wrapper around {@link org.aspectj.lang.JoinPoint} to make easy retrieve data from a certain
 * {@link org.aspectj.lang.JoinPoint} passed as a parameter in the constructor.
 */
public class GandalfJoinPoint {

  private final JoinPoint joinPoint;
  private final MethodSignature methodSignature;
  private final String className;
  private final String methodName;
  private final List<String> methodParamNamesList;
  private final List<Object> methodParamValuesList;
  private final String executionThreadName;

  /**
   * Constructor of the class
   *
   * @param joinPoint object to wrap around.
   */
  public GandalfJoinPoint(JoinPoint joinPoint) {
    if (joinPoint == null) {
      throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
    }

    this.joinPoint = joinPoint;
    this.methodSignature = (MethodSignature) this.joinPoint.getSignature();
    this.className = this.methodSignature.getDeclaringType().getSimpleName();
    this.methodName = this.methodSignature.getName();
    this.methodParamNamesList = Arrays.asList(this.methodSignature.getParameterNames());
    this.methodParamValuesList = Arrays.asList(this.joinPoint.getArgs());
    this.executionThreadName = Thread.currentThread().getName();
  }

  public String getClassName() {
    return className;
  }

  public String getMethodName() {
    return methodName;
  }

  public List<String> getMethodParamNamesList() {
    return methodParamNamesList;
  }

  public List<Object> getMethodParamValuesList() {
    return methodParamValuesList;
  }

  public String getExecutionThreadName() {
    return executionThreadName;
  }

  /**
   * Gets an annotation from a method, will return null in case it does not exist.
   * It is important to have a retention policy of
   * {@link java.lang.annotation.RetentionPolicy#RUNTIME} to avoid null values when reading them.
   *
   * @param annotation The {@link java.lang.annotation.Annotation} to get.
   * @return The annotation if exists, otherwise null.
   */
  public Annotation getAnnotation(Class<? extends Annotation> annotation) {
    return methodSignature.getMethod().getAnnotation(annotation);
  }

  /**
   * Check if the {@link JoinPoint} is being executed in the main thread.
   *
   * @return true: main thread, otherwise false.
   */
  public boolean isMainThread() {
    return (Looper.getMainLooper() == Looper.myLooper());
  }

  /**
   * Check if the {@link JoinPoint} has a return type.
   *
   * @param joinPoint the {@link JoinPoint} to check.
   * @return true if there is a return type, false if it is void.
   */
  public boolean hasReturnType(JoinPoint joinPoint) {
    Signature signature = joinPoint.getSignature();

    return (signature instanceof MethodSignature
        && ((MethodSignature) signature).getReturnType() != void.class);
  }
}
