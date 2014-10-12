/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.gandalf;

import com.fernandocejas.android10.gandalf.annotation.GLog;
import com.fernandocejas.android10.gandalf.annotation.GStrictMode;
import com.fernandocejas.android10.gandalf.annotation.GTrace;
import com.fernandocejas.android10.gandalf.annotation.GWatch;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.fernandocejas.android10.gandalf.annotation.GLog.LogScope.EVERYTHING;
import static com.fernandocejas.android10.gandalf.annotation.GLog.LogScope.SIGNATURE;
import static com.fernandocejas.android10.gandalf.annotation.GLog.LogScope.THREAD;
import static com.fernandocejas.android10.gandalf.annotation.GLog.LogScope.TIME;

public class DataPresenter {

  @GLog(EVERYTHING)
  public void getData() {
    sleep(5);
  }

  @GLog(TIME)
  public void getData(String name) {
    sleep(5);
  }

  @GLog(SIGNATURE)
  public void getData(String name, int quantity) {
    sleep(5);
  }

  @GLog(THREAD)
  public void getData(String name, int quantity, int count) {
    sleep(5);
  }

  @GTrace
  public void printMessage(String tag, String message) {
    sleep(10);
  }

  @GWatch
  public void anotherMethodOne() {
    sleep(25);
  }

  @GWatch
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

  @GStrictMode
  public void executeDiskIOTaskOnUiThread() {
    try {
      File file = File.createTempFile("test", ".txt");
      FileWriter writer = new FileWriter(file);
      writer.write("This is fernando julian cejas testing something");
      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
    }
  }
}
