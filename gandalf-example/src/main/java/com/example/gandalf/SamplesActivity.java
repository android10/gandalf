package com.example.gandalf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.gandalf.sample.GLogSample;
import com.fernandocejas.android10.gandalf.Gandalf;

public class SamplesActivity extends Activity {

  private Button btn_sampleGLogAnnotation;
  private Button btn_sampleGTraceAnnotation;
  private Button btn_sampleGWatchAnnotation;
  private Button btn_sampleStrictModeAnnotation;
  private Button btn_sampleGandalfStats;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_samples);
    this.mapGUI();
  }

  private void mapGUI() {
    this.btn_sampleGLogAnnotation = (Button)findViewById(R.id.btn_sampleGLogAnnotation);
    this.btn_sampleGTraceAnnotation = (Button)findViewById(R.id.btn_sampleGTraceAnnotation);
    this.btn_sampleGWatchAnnotation = (Button)findViewById(R.id.btn_sampleGWatchAnnotation);
    this.btn_sampleStrictModeAnnotation = (Button)findViewById(R.id.btn_sampleStrictModeAnnotation);
    this.btn_sampleGandalfStats = (Button)findViewById(R.id.btn_sampleGandalfStats);

    this.btn_sampleGLogAnnotation.setOnClickListener(buttonSampleGLogListener);
    this.btn_sampleGTraceAnnotation.setOnClickListener(buttonSampleGTraceListener);
    this.btn_sampleGWatchAnnotation.setOnClickListener(buttonSampleGWatchListener);
    this.btn_sampleStrictModeAnnotation.setOnClickListener(buttonSampleStrictModeListener);
    this.btn_sampleGandalfStats.setOnClickListener(buttonSampleGandalfStatsListener);
  }

  private View.OnClickListener buttonSampleGLogListener = new View.OnClickListener() {
    @Override public void onClick(View v) {
      GLogSample gLogSample = new GLogSample();
      gLogSample.doSomething();
      gLogSample.doSomethingElse(1, "android");
      gLogSample.doSomethingAndLogOnlyTime();
    }
  };

  private View.OnClickListener buttonSampleGTraceListener = new View.OnClickListener() {
    @Override public void onClick(View v) {

    }
  };

  private View.OnClickListener buttonSampleGWatchListener = new View.OnClickListener() {
    @Override public void onClick(View v) {

    }
  };

  private View.OnClickListener buttonSampleStrictModeListener = new View.OnClickListener() {
    @Override public void onClick(View v) {

    }
  };

  private View.OnClickListener buttonSampleGandalfStatsListener = new View.OnClickListener() {
    @Override public void onClick(View v) {
      Gandalf.printStats();
    }
  };
}
