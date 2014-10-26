package com.example.gandalf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.gandalf.sample.GLogSample;
import com.example.gandalf.sample.GStrictModeSample;
import com.example.gandalf.sample.GTraceSample;
import com.example.gandalf.sample.GWatchSample;
import com.fernandocejas.android10.gandalf.Gandalf;
import com.fernandocejas.android10.gandalf.annotation.GStrictMode;

public class SamplesActivity extends Activity {

  private Button btn_sampleGLogAnnotation;
  private Button btn_sampleGTraceAnnotation;
  private Button btn_sampleGWatchAnnotation;
  private Button btn_sampleStrictModeAnnotation;
  private Button btn_sampleGandalfStats;

  @GStrictMode
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
      GTraceSample gTraceSample = new GTraceSample();
      gTraceSample.doSomethingOnMainThread();
      gTraceSample.doSomethingOnAnotherThread("SomeValue");
    }
  };

  private View.OnClickListener buttonSampleGWatchListener = new View.OnClickListener() {
    @Override public void onClick(View v) {
      GWatchSample gWatchSample = new GWatchSample();
      gWatchSample.doSomething();
      gWatchSample.doSomething();
      gWatchSample.doSomething();
      gWatchSample.DoSomethingManyTimes(12);
      Gandalf.disableInspectionMode();
      Gandalf.printStats();
    }
  };

  private View.OnClickListener buttonSampleStrictModeListener = new View.OnClickListener() {
    @Override public void onClick(View v) {
      //REMEMBER: It is not necessary to annotate each method/constructor, it is enough with
      //just using the annotation on the main entry point of your app to activate StricMode.
      //Thus this feature will live along the execution of your app.
      //In this example we are annotating the onCreate() method of this activity.
      GStrictModeSample gStrictModeSample = new GStrictModeSample();
      gStrictModeSample.executeDiskIOTaskOnUiThread();
    }
  };

  private View.OnClickListener buttonSampleGandalfStatsListener = new View.OnClickListener() {
    @Override public void onClick(View v) {
      Gandalf.enableInspectionMode();
      Gandalf.printStats();
    }
  };
}
