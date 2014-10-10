/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf;

import com.fernandocejas.android10.gandalf.aspect.TrackingAspect;
import com.fernandocejas.android10.gandalf.internal.MessageManager;
import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPoint;
import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPointStats;
import java.util.Map;
import org.aspectj.lang.Aspects;

/**
 * <br>Gandalf development and debug utilities.<br>
 * You can use this class to setup the library.<br>
 * It also contains other utility methods to print stats, etc.<br>
 *
 * <br>This library will always run in DEBUG MODE (development mode), this means all the
 * information or stats will be printed only when you are using the DEBUG TYPE of your build, so
 * it is IMPORTANT that you use 'installDebug' gradle task to see the results, otherwise you will
 * not see anything.<br>
 *
 * <br>REMEMBER: it is not necessary to remove Gandalf code or annotations, even if you are
 * building a release version of the app, cause nothing will be shown in production or release
 * mode.<br>
 *
 * <br>------------------------------------------------------------------------------------------
 * <br>"Keep well the Lord of the Mark, till I return. Await me at Helm's Gate. Farewell!"
 * –Gandalf to Aragorn and Éomer and the men of the king's household.
 * <br>------------------------------------------------------------------------------------------
 */
public final class Gandalf {

  private static boolean inspectMode = true;

  private Gandalf() {}

  /**
   * Enable Gandalf to collect stats automatically.<br>
   * To see the stats, at any point, you just call
   * {@link com.fernandocejas.android10.gandalf.Gandalf#printStats()}, thus, the information will
   * be printed in the logcat only in debug mode.
   */
  public static void enableInspectionMode() {
    inspectMode = true;
  }

  /**
   * Disable Gandalf to collect stats automatically.<br>
   * To enable this flag again call {@link #enableInspectionMode()}.
   */
  public static void disableInspectionMode() {
    inspectMode = false;
  }

  /**
   * Print both stats collected by annotation
   * {@link com.fernandocejas.android10.gandalf.annotation.Trackable} and Gandalf collected
   * stats if Inspection Mode is enable via method {@link #enableInspectionMode()}.<br>
   *
   * This will be shown in the logcat only in DEBUG mode.
   */
  public static void printStats() {
    if (inspectMode) {
      printGandalfCollectedStats();
    }
    printTrackableStats();
  }

  private static void printGandalfCollectedStats() {
    //TODO print gandalf collected stats.
  }

  private static void printTrackableStats() {
    Map<GandalfJoinPoint, GandalfJoinPointStats> statsMap =
        Aspects.aspectOf(TrackingAspect.class).getStatsMap();

    new MessageManager().printTrackingAspectStats(statsMap);
  }
}
