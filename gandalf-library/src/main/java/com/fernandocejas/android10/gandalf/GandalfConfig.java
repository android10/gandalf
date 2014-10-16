/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.gandalf;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;

final class GandalfConfig {

  private static class LazyHolder {
    private static final GandalfConfig INSTANCE = new GandalfConfig();
  }

  protected static GandalfConfig getInstance() {
    return LazyHolder.INSTANCE;
  }

  private static final String CERTIFICATE_TYPE = "X.509";
  private static final String DEBUG_KEY_NAME = "CN=Android Debug,O=Android,C=US";
  private static final X500Principal DEBUG_DN = new X500Principal(DEBUG_KEY_NAME);

  private boolean inspectMode;
  private final boolean debuggable;

  private GandalfConfig() {
    this.inspectMode = true;
    this.debuggable = this.isAppSignedWithDebugCertificate(GandalfApplication.getContext());
  }

  protected boolean isDebuggable() {
    return debuggable;
  }

  protected boolean isInspectMode() {
    return inspectMode;
  }

  protected void setInspectMode(boolean inspectMode) {
    this.inspectMode = inspectMode;
  }

  private boolean isAppSignedWithDebugCertificate(Context context) {
    boolean debuggable = false;

    try {
      PackageInfo packageInfo = context.getApplicationContext().getPackageManager()
          .getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
      Signature signatures[] = packageInfo.signatures;
      CertificateFactory cf = CertificateFactory.getInstance(CERTIFICATE_TYPE);

      for (int i = 0; i < signatures.length; i++) {
        ByteArrayInputStream stream = new ByteArrayInputStream(signatures[i].toByteArray());
        X509Certificate cert = (X509Certificate) cf.generateCertificate(stream);
        debuggable = cert.getSubjectX500Principal().equals(DEBUG_DN);
        if (debuggable) { break; }
      }
    } catch (PackageManager.NameNotFoundException e) {
      //debuggable variable will remain false
    } catch (CertificateException e) {
      //debuggable variable will remain false
    }

    return debuggable;
  }
}
