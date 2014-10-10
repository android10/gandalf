/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclarePrecedence;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@DeclarePrecedence("com.fernandocejas.android10.gandalf.aspect.TrackingAspect")
public class InspectingAspect {

  private static final String POINTCUT_ACTIVITY_CREATE =
      "execution(void *android.app.Activity+.*onCreate(..))";

  private static final String POINTCUT_FRAGMENT_CREATE =
      "execution(void *android.app.Fragment+.*onCreate(..))";

  private static final String POINTCUT_FRAGMENT_SUPPORT_CREATE =
      "execution(void *android.support.v4.app.Fragment+.*onCreate(..))";

  private static final String POINTCUT_SERVICE_CREATE =
      "execution(void *android.app.Service+.*onCreate(..))";

  private int activitiesCreated = 0;
  private int fragmentsCreated = 0;
  private int servicesCreated = 0;

  @Pointcut(POINTCUT_ACTIVITY_CREATE)
  public void onActivityCreated(){}

  @Pointcut(POINTCUT_FRAGMENT_CREATE)
  public void onFragmentCreated(){}

  @Pointcut(POINTCUT_FRAGMENT_SUPPORT_CREATE)
  public void onFragmentSupportCreated(){}

  @Pointcut(POINTCUT_SERVICE_CREATE)
  public void onServiceCreated(){}

  @After("onActivityCreated()")
  public void afterActivityCreated() {
    this.activitiesCreated++;
  }

  @After("onFragmentCreated() || onFragmentSupportCreated()")
  public void afterFragmentCreated() {
    this.fragmentsCreated++;
  }

  @After("onServiceCreated()")
  public void afterServiceCreated() {
    this.servicesCreated++;
  }

  public int getActivitiesCreated() {
    return activitiesCreated;
  }

  public int getFragmentsCreated() {
    return fragmentsCreated;
  }

  public int getServicesCreated() {
    return servicesCreated;
  }
}
