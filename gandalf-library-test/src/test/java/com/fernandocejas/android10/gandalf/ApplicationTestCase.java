/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf;

import java.lang.reflect.Field;
import org.junit.runner.RunWith;

@RunWith(ApplicationTestRunner.class)
public class ApplicationTestCase {

  protected void resetSingleton(Class clazz) {
    Field instance;
    try {
      instance = clazz.getDeclaredField("INSTANCE");
      instance.setAccessible(true);
      instance.set(null, null);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
