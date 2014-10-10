package com.example.gandalf;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.fernandocejas.android10.gandalf.Gandalf;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

    dataPresenterOne.executeDiskIOTaskOnUiThread();

    //this.executeNetworkTaskOnUiThread();
    //this.executeDiskIOTaskOnUiThread();
  }

  @Override protected void onDestroy() {
    super.onDestroy();

    //dataPresenterOne.executeDiskIOTaskOnUiThread();

    Gandalf.printStats();
  }

  private void executeNetworkTaskOnUiThread() {
    try {
      URL url = new URL("http://www.fernandocejas.com");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      readStream(con.getInputStream());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void readStream(InputStream in) {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(in));
      String line = "";
      while ((line = reader.readLine()) != null) {
        Log.d("Penano", "Lineeee ----> " + line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
