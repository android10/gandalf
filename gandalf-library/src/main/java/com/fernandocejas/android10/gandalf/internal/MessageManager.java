package com.fernandocejas.android10.gandalf.internal;

import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPoint;

public class MessageManager {

  private final MessageBuilder messageBuilder;
  private final DebugLog debugLog;

  public MessageManager() {
    this(new MessageBuilder(), new DebugLog());
  }

  public MessageManager(MessageBuilder messageBuilder, DebugLog debugLog) {
    this.messageBuilder = messageBuilder;
    this.debugLog = debugLog;
  }

  private void printMessage(String tag, String message) {
    this.debugLog.log(tag, message);
  }

  public void printTracingAspectEnterMessage(GandalfJoinPoint joinPoint) {
    String message = this.messageBuilder.buildTracingAspectEnterMessage(joinPoint);
    this.printMessage(joinPoint.getClassName(), message);
  }

  public void printTracingAspectExitMessage(GandalfJoinPoint joinPoint, Object returnValue,
      String executionTimeMillis) {
    String message = this.messageBuilder.buildTracingAspectExitMessage(joinPoint, returnValue,
        executionTimeMillis);
    this.printMessage(joinPoint.getClassName(), message);
  }

  public void printLoggingAspectMessageEverything(GandalfJoinPoint joinPoint, Object returnValue,
      String executionTimeMillis) {
    String message = this.messageBuilder.buildLoggingAspectMessageEverything(joinPoint,
        returnValue, executionTimeMillis);
    this.printMessage(joinPoint.getClassName(), message);
  }

  public void printLoggingAspectMessageSignature(GandalfJoinPoint joinPoint, Object returnValue) {
    String message = this.messageBuilder.buildLoggingAspectMessageSignature(joinPoint,
        returnValue);
    this.printMessage(joinPoint.getClassName(), message);
  }

  public void printLoggingAspectMessageThread(GandalfJoinPoint joinPoint) {
    String message = this.messageBuilder.buildLoggingAspectMessageThread(joinPoint);
    this.printMessage(joinPoint.getClassName(), message);
  }

  public void printLoggingAspectMessageTime(GandalfJoinPoint joinPoint,
      String executionTimeMillis) {
    String message = this.messageBuilder.buildLoggingAspectMessageTime(joinPoint,
        executionTimeMillis);
    this.printMessage(joinPoint.getClassName(), message);
  }
}
