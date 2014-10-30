/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.joinpoint;

import com.fernandocejas.android10.gandalf.ApplicationTestCase;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GandalfJoinPointTest extends ApplicationTestCase {

  @Before
  public void setUp() {
  }

  @Test
  public void testDummy() {
    assertThat(true, is(true));
  }
}
