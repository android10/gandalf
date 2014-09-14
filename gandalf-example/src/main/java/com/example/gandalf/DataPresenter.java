/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.gandalf;

import com.fernandocejas.android10.gandalf.annotation.Loggable;
import com.fernandocejas.android10.gandalf.annotation.Traceable;

public class DataPresenter {

  @Loggable
  public void getData() {
    sleep(5);
  }

  @Traceable
  public void printMessage(String tag, String message) {
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
