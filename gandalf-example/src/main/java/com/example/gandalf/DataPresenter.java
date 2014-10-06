/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.gandalf;

import com.fernandocejas.android10.gandalf.annotation.Trackable;
import com.fernandocejas.android10.gandalf.annotation.Loggable;
import com.fernandocejas.android10.gandalf.annotation.Traceable;

import static com.fernandocejas.android10.gandalf.annotation.Loggable.LoggingLevel.EVERYTHING;
import static com.fernandocejas.android10.gandalf.annotation.Loggable.LoggingLevel.SIGNATURE;
import static com.fernandocejas.android10.gandalf.annotation.Loggable.LoggingLevel.THREAD;
import static com.fernandocejas.android10.gandalf.annotation.Loggable.LoggingLevel.TIME;

public class DataPresenter {

  @Loggable(EVERYTHING)
  public void getData() {
    sleep(5);
  }

  @Loggable(TIME)
  public void getData(String name) {
    sleep(5);
  }

  @Loggable(SIGNATURE)
  public void getData(String name, int quantity) {
    sleep(5);
  }

  @Loggable(THREAD)
  public void getData(String name, int quantity, int count) {
    sleep(5);
  }

  @Traceable
  public void printMessage(String tag, String message) {
    sleep(10);
  }

  @Trackable
  public void anotherMethodOne() {
    sleep(25);
  }

  @Trackable
  public void anotherMethodTwo() {
    sleep(18);
  }

  private void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
