/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.internal;

import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPoint;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

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
    setupMocks();
  }

  @After
  public void tearDown() {
    verifyLogcat();
  }

  private void setupMocks() {
    given(mockGandalfJoinPoint.getClassSimpleName()).willReturn(anyString());
  }

  private void verifyLogcat() {
    verify(mockDebugLog).log(anyString(), anyString());
  }

  @Test
  public void testTracingAspectEnterMessage() {
    messageManager.printTracingAspectEnterMessage(mockGandalfJoinPoint);

    verify(mockMessageBuilder).buildTracingAspectEnterMessage(mockGandalfJoinPoint);
  }

  @Test
  public void testTracingAspectExitMessage() {
    messageManager.printTracingAspectExitMessage(mockGandalfJoinPoint, anyObject(), anyString());

    verify(mockMessageBuilder).buildTracingAspectExitMessage(eq(mockGandalfJoinPoint), anyObject(), anyString());
  }
}
