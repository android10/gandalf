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

  public void printTraceEnteringMessage(GandalfJoinPoint joinPoint) {
    String enterMessage = this.messageBuilder.buildTraceEnterMessage(joinPoint);
    this.printMessage(joinPoint.getClassName(), enterMessage);
  }

  public void printTraceExitingMessage(GandalfJoinPoint joinPoint, Object returnValue,
      String executionTimeMillis) {
    String exitMessage = this.messageBuilder.buildTraceExitMessage(joinPoint, returnValue,
        executionTimeMillis);
    this.printMessage(joinPoint.getClassName(), exitMessage);
  }

  public void printLogExitingMessage(GandalfJoinPoint joinPoint, Object returnValue,
      String executionTimeMillis) {
    String exitMessage = this.messageBuilder.buildLogExitMessage(joinPoint, returnValue,
        executionTimeMillis);
    this.printMessage(joinPoint.getClassName(), exitMessage);
  }
}
