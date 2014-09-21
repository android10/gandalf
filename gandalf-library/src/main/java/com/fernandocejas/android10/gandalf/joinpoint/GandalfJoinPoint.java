/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.joinpoint;

import java.util.Arrays;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Wrapper around {@link org.aspectj.lang.JoinPoint} to make easy retrieve data from a certain
 * {@link org.aspectj.lang.JoinPoint} passed as a parameter in the constructor.
 */
public class GandalfJoinPoint {

  private final String className;
  private final String methodName;
  private final List<String> methodParamNamesList;
  private final List<Object> methodParamValuesList;
  private final String executionThreadName;

  /**
   * Constructor of the class
   * @param JoinPoint object to wrap around.
   */
  public GandalfJoinPoint(JoinPoint JoinPoint) {
    if (JoinPoint == null) {
      throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
    }

    MethodSignature methodSignature = (MethodSignature) JoinPoint.getSignature();
    this.className = methodSignature.getDeclaringType().getSimpleName();
    this.methodName = methodSignature.getName();
    this.methodParamNamesList = Arrays.asList(methodSignature.getParameterNames());
    this.methodParamValuesList = Arrays.asList(JoinPoint.getArgs());
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
}
