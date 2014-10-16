/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf;

import android.app.Application;
import android.content.Context;

public final class GandalfApplication extends Application {

  private static GandalfApplication INSTANCE;

  public GandalfApplication() {
    INSTANCE = this;
  }

  public static Context getContext() {
    return INSTANCE.getApplicationContext();
  }
}
