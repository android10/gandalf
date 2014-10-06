package com.example.gandalf;

import android.app.Activity;
import android.os.Bundle;
import com.fernandocejas.android10.gandalf.Gandalf;

public class MainActivity extends Activity {

  private final DataPresenter dataPresenterOne = new DataPresenter();
  private final DataPresenter dataPresenterTwo = new DataPresenter();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //dataPresenterOne.getData();
    //dataPresenterOne.getData("pi");
    //dataPresenterOne.getData("android10");
    //dataPresenterOne.getData("fernando", 1);
    //dataPresenterOne.getData("fernando", 1, 1);

    dataPresenterOne.anotherMethodOne();
    dataPresenterOne.anotherMethodOne();
    dataPresenterOne.anotherMethodOne();
    dataPresenterTwo.anotherMethodOne();

    dataPresenterTwo.anotherMethodTwo();
    dataPresenterTwo.anotherMethodTwo();

    dataPresenterOne.printMessage("this is tag", "this is message");
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    Gandalf.printStats();
  }
}
