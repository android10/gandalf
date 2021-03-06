/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.joinpoint;

public class GandalfJoinPointStats {

  private final GandalfJoinPoint gandalfJoinPoint;

  private int timesExecuted = 0;
  private long totalExecutionTime = 0;

  public GandalfJoinPointStats(GandalfJoinPoint gandalfJoinPoint) {
    if (gandalfJoinPoint == null) {
      throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
    }
    this.gandalfJoinPoint = gandalfJoinPoint;
  }

  public GandalfJoinPoint getGandalfJoinPoint() {
    return gandalfJoinPoint;
  }

  public synchronized void incrementTimesExecuted() {
    this.timesExecuted++;
  }

  public synchronized void accumulateExecutionTime(long executionTimeMillis) {
    this.totalExecutionTime+= executionTimeMillis;
  }

  public synchronized int getJoinPointTimesExecuted() {
    return this.timesExecuted;
  }

  public synchronized long getJoinPointTotalExecutionTimeMillis() {
    return this.totalExecutionTime;
  }
}
