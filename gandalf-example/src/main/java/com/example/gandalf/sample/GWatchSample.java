/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.gandalf.sample;

import com.fernandocejas.android10.gandalf.annotation.GWatch;

public class GWatchSample {

  public GWatchSample() {}

  @GWatch
  public void doSomething() {
    sleep(10);
  }

  public void DoSomethingManyTimes(int numberOfTimes) {
    for (int i=1; i<=numberOfTimes; i++) {
      doJob();
    }
  }

  @GWatch
  private void doJob() {
    sleep(2);
  }

  private void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
