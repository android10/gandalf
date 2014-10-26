/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.gandalf.sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GStrictModeSample {

  private static final String TAG = "StrictModeSample";

  public GStrictModeSample() {}

  public void executeDiskIOTaskOnUiThread() {
    try {
      File file = File.createTempFile("test", ".txt");
      FileWriter writer = new FileWriter(file);
      writer.write("This is fernando testing something");
      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
    }
  }
}
