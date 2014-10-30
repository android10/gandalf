/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.joinpoint;

import com.fernandocejas.android10.gandalf.ApplicationTestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GandalfJoinPointTest extends ApplicationTestCase {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void testGandalfJoinPointCreation() {
    expectedException.expect(IllegalArgumentException.class);
    GandalfJoinPoint gandalfJoinPoint = new GandalfJoinPoint(null);
  }
}
