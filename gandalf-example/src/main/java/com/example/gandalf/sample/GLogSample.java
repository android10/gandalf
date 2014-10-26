/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.gandalf.sample;

import com.fernandocejas.android10.gandalf.annotation.GLog;

import static com.fernandocejas.android10.gandalf.annotation.GLog.LogScope;

public class GLogSample {

  public GLogSample(){}

  @GLog
  public void doSomething() {
    sleep(80);
  }

  @GLog
  public void doSomethingElse(int someValue, String someString) {
    sleep(20);
  }

  @GLog(LogScope.TIME)
  public void doSomethingAndLogOnlyTime() {
    sleep(12);
  }

  private void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
