/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.gandalf.sample;

import com.fernandocejas.android10.gandalf.annotation.GTrace;

public class GTraceSample {

  public GTraceSample() {}

  @GTrace
  public void doSomethingOnMainThread() {
    try {
      Thread.sleep(20);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void doSomethingOnAnotherThread(String value) {
    Thread thread = new Thread(new MyHeavyJob());
    thread.setName("BackgroundThread");
    thread.start();
  }

  private void sleep(long millis) {

  }

  private static class MyHeavyJob implements Runnable {
    @GTrace
    private void doSomeHeadyJob() {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    @Override public void run() {
      doSomeHeadyJob();
    }
  }
}
