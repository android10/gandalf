package com.example.gandalf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.fernandocejas.android10.gandalf.Gandalf;

public class MainActivity extends Activity {

  private Button btn_LoadExamples;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    this.mapGUI();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    Gandalf.printStats();
  }

  private void mapGUI() {
    btn_LoadExamples = (Button) findViewById(R.id.btn_LoadExamples);
    btn_LoadExamples.setOnClickListener(loadExamplesOnClickListener);
  }

  private final View.OnClickListener loadExamplesOnClickListener = new View.OnClickListener() {
    @Override public void onClick(View v) {
      Intent intent = new Intent(MainActivity.this, SamplesActivity.class);
      startActivity(intent);
    }
  };
}
