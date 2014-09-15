package com.example.gandalf;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

  private final DataPresenter dataPresenter = new DataPresenter();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    dataPresenter.getData();

    dataPresenter.anotherMethod();
    dataPresenter.anotherMethod();
    dataPresenter.anotherMethod();

    dataPresenter.printMessage("this is tag", "this is message");
  }
}
