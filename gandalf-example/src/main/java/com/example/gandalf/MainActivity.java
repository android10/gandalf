package com.example.gandalf;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

  private final DataPresenter dataPresenterOne = new DataPresenter();
  private final DataPresenter dataPresenterTwo = new DataPresenter();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    dataPresenterOne.getData();

    dataPresenterOne.anotherMethod();
    dataPresenterOne.anotherMethod();
    dataPresenterOne.anotherMethod();

    dataPresenterTwo.anotherMethod();
    dataPresenterTwo.anotherMethod();
    dataPresenterTwo.anotherMethod();

    dataPresenterOne.printMessage("this is tag", "this is message");
  }
}
