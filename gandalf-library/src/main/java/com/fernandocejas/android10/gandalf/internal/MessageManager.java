package com.fernandocejas.android10.gandalf.internal;

import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPoint;
import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPointStats;
import java.util.Map;

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
    this.printMessage(joinPoint.getClassSimpleName(), message);
  }

  public void printTracingAspectExitMessage(GandalfJoinPoint joinPoint, Object returnValue,
      String executionTimeMillis) {
    String message = this.messageBuilder.buildTracingAspectExitMessage(joinPoint, returnValue,
        executionTimeMillis);
    this.printMessage(joinPoint.getClassSimpleName(), message);
  }

  public void printLoggingAspectMessageEverything(GandalfJoinPoint joinPoint, Object returnValue,
      String executionTimeMillis) {
    String message = this.messageBuilder.buildLoggingAspectMessageEverything(joinPoint,
        returnValue, executionTimeMillis);
    this.printMessage(joinPoint.getClassSimpleName(), message);
  }

  public void printLoggingAspectMessageSignature(GandalfJoinPoint joinPoint, Object returnValue) {
    String message = this.messageBuilder.buildLoggingAspectMessageSignature(joinPoint,
        returnValue);
    this.printMessage(joinPoint.getClassSimpleName(), message);
  }

  public void printLoggingAspectMessageThread(GandalfJoinPoint joinPoint) {
    String message = this.messageBuilder.buildLoggingAspectMessageThread(joinPoint);
    this.printMessage(joinPoint.getClassSimpleName(), message);
  }

  public void printLoggingAspectMessageTime(GandalfJoinPoint joinPoint,
      String executionTimeMillis) {
    String message = this.messageBuilder.buildLoggingAspectMessageTime(joinPoint,
        executionTimeMillis);
    this.printMessage(joinPoint.getClassSimpleName(), message);
  }

  public void printWatchingAspectStats(Map<GandalfJoinPoint, GandalfJoinPointStats> statsMap) {
    String message;
    if (statsMap != null && !statsMap.isEmpty()) {
      for (GandalfJoinPointStats stats : statsMap.values()) {
        message = this.messageBuilder.buildWatchingAspectMessageStats(stats);
        this.printMessage(stats.getGandalfJoinPoint().getClassSimpleName(), message);
      }
    }
  }

  public void printInspectingAspectStats(int activitiesCreated, int fragmentsCreated,
      int servicesCreated) {
    if (activitiesCreated > 0) {
      String message = this.messageBuilder.buildInspectingAspectStatsMessage(
          MessageBuilder.GANDALF_STATS_MESSAGE_ACTIVITIES_CREATED, activitiesCreated);
      this.printMessage(MessageBuilder.GANDALF_STATS_MESSAGE_TAG, message);
    }
    if (fragmentsCreated > 0) {
      String message = this.messageBuilder.buildInspectingAspectStatsMessage(
          MessageBuilder.GANDALF_STATS_MESSAGE_FRAGMENTS_CREATED, fragmentsCreated);
      this.printMessage(MessageBuilder.GANDALF_STATS_MESSAGE_TAG, message);
    }
    if (servicesCreated > 0) {
      String message = this.messageBuilder.buildInspectingAspectStatsMessage(
          MessageBuilder.GANDALF_STATS_MESSAGE_SERVICES_CREATED, servicesCreated);
      this.printMessage(MessageBuilder.GANDALF_STATS_MESSAGE_TAG, message);
    }
  }
}
