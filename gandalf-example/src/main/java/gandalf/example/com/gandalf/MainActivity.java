package gandalf.example.com.gandalf;

import android.app.Activity;
import android.os.Bundle;
import org.android10.gandalf.annotation.DebugTrace;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    sleepForAWhile();
  }

  @DebugTrace
  private void sleepForAWhile() {
    sleep(5);
  }

  private void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
