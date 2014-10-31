/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.internal;

import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPoint;
import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPointStats;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class MessageManagerTest {

  private MessageManager messageManager;

  @Mock
  private MessageBuilder mockMessageBuilder;
  @Mock
  private DebugLog mockDebugLog;
  @Mock
  private GandalfJoinPoint mockGandalfJoinPoint;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    messageManager = new MessageManager(mockMessageBuilder, mockDebugLog);
  }

  private void setupMocks() {
    given(mockGandalfJoinPoint.getClassSimpleName()).willReturn(anyString());
  }

  private void verifyLogcat() {
    verify(mockDebugLog).log(anyString(), anyString());
  }

  @Test
  public void testTracingAspectEnterMessage() {
    setupMocks();

    messageManager.printTracingAspectEnterMessage(mockGandalfJoinPoint);

    verify(mockMessageBuilder).buildTracingAspectEnterMessage(mockGandalfJoinPoint);
    verifyLogcat();
  }

  @Test
  public void testTracingAspectExitMessage() {
    setupMocks();

    messageManager.printTracingAspectExitMessage(mockGandalfJoinPoint, anyObject(), anyString());

    verify(mockMessageBuilder).buildTracingAspectExitMessage(eq(mockGandalfJoinPoint), anyObject(), anyString());
    verifyLogcat();
  }

  @Test
  public void testPrintLoggingAspectMessageEverything() {
    setupMocks();

    messageManager.printLoggingAspectMessageEverything(mockGandalfJoinPoint, anyObject(),
        anyString());

    verify(mockMessageBuilder).buildLoggingAspectMessageEverything(eq(mockGandalfJoinPoint),
        anyObject(), anyString());
    verifyLogcat();
  }

  @Test
  public void testPrintLoggingAspectMessageSignature() {
    setupMocks();

    messageManager.printLoggingAspectMessageSignature(mockGandalfJoinPoint, anyObject());

    verify(mockMessageBuilder).buildLoggingAspectMessageSignature(eq(mockGandalfJoinPoint),
        anyObject());
    verifyLogcat();
  }

  @Test
  public void testPrintLoggingAspectMessageThread() {
    setupMocks();

    messageManager.printLoggingAspectMessageThread(mockGandalfJoinPoint);

    verify(mockMessageBuilder).buildLoggingAspectMessageThread(mockGandalfJoinPoint);
    verifyLogcat();
  }

  @Test
  public void testPrintLoggingAspectMessageTime() {
    setupMocks();

    messageManager.printLoggingAspectMessageTime(mockGandalfJoinPoint, anyString());

    verify(mockMessageBuilder).buildLoggingAspectMessageTime(eq(mockGandalfJoinPoint), anyString());
    verifyLogcat();
  }

  @Test
  public void testPrintWatchingAspectStats() {
    GandalfJoinPoint mockGandalfJoinPointOne = mock(GandalfJoinPoint.class);
    GandalfJoinPoint mockGandalfJoinPointTwo = mock(GandalfJoinPoint.class);
    GandalfJoinPointStats mockGandalfJoinPointStatsOne = mock(GandalfJoinPointStats.class);
    GandalfJoinPointStats mockGandalfJoinPointStatsTwo = mock(GandalfJoinPointStats.class);

    given(mockGandalfJoinPointStatsOne.getGandalfJoinPoint()).willReturn(mockGandalfJoinPointOne);
    given(mockGandalfJoinPointStatsTwo.getGandalfJoinPoint()).willReturn(mockGandalfJoinPointTwo);

    Map<GandalfJoinPoint, GandalfJoinPointStats> statsMap = new HashMap<GandalfJoinPoint, GandalfJoinPointStats>();
    statsMap.put(mockGandalfJoinPointOne, mockGandalfJoinPointStatsOne);
    statsMap.put(mockGandalfJoinPointTwo, mockGandalfJoinPointStatsTwo);

    messageManager.printWatchingAspectStats(statsMap);

    verify(mockMessageBuilder).buildWatchingAspectMessageStats(mockGandalfJoinPointStatsOne);
    verify(mockMessageBuilder).buildWatchingAspectMessageStats(mockGandalfJoinPointStatsTwo);
    verify(mockDebugLog, times(statsMap.size())).log(anyString(), anyString());
  }

  @Test
  public void testPrintWatchingAspectStatsEmptyMap() {
    messageManager.printWatchingAspectStats(null);

    verifyZeroInteractions(mockMessageBuilder, mockDebugLog);
  }

  @Test
  public void testPrintInspectingAspectStats() {
    messageManager.printInspectingAspectStats(2, 4, 5);

    verify(mockMessageBuilder).buildInspectingAspectStatsMessage(MessageBuilder.GANDALF_STATS_MESSAGE_ACTIVITIES_CREATED, 2);
    verify(mockMessageBuilder).buildInspectingAspectStatsMessage(MessageBuilder.GANDALF_STATS_MESSAGE_FRAGMENTS_CREATED, 4);
    verify(mockMessageBuilder).buildInspectingAspectStatsMessage(MessageBuilder.GANDALF_STATS_MESSAGE_SERVICES_CREATED, 5);
  }

  @Test
  public void testPrintInspectingAspectStatsEmpty() {
    messageManager.printInspectingAspectStats(0, 0, 0);

    verifyZeroInteractions(mockMessageBuilder, mockDebugLog);
  }
}
