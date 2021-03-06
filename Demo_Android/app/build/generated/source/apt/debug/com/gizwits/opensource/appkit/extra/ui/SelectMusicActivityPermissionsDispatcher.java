// This file was generated by PermissionsDispatcher. Do not modify!
package com.gizwits.opensource.appkit.extra.ui;

import android.support.v4.app.ActivityCompat;
import java.lang.Override;
import java.lang.String;
import java.lang.ref.WeakReference;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;

final class SelectMusicActivityPermissionsDispatcher {
  private static final int REQUEST_GETMUSICFILE = 0;

  private static final String[] PERMISSION_GETMUSICFILE = new String[] {"android.permission.RECORD_AUDIO"};

  private SelectMusicActivityPermissionsDispatcher() {
  }

  static void getMusicFileWithCheck(SelectMusicActivity target) {
    if (PermissionUtils.hasSelfPermissions(target, PERMISSION_GETMUSICFILE)) {
      target.getMusicFile();
    } else {
      if (PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_GETMUSICFILE)) {
        target.showRationaleForRecord(new GetMusicFilePermissionRequest(target));
      } else {
        ActivityCompat.requestPermissions(target, PERMISSION_GETMUSICFILE, REQUEST_GETMUSICFILE);
      }
    }
  }

  static void onRequestPermissionsResult(SelectMusicActivity target, int requestCode, int[] grantResults) {
    switch (requestCode) {
      case REQUEST_GETMUSICFILE:
      if (PermissionUtils.getTargetSdkVersion(target) < 23 && !PermissionUtils.hasSelfPermissions(target, PERMISSION_GETMUSICFILE)) {
        target.showDeniedForCamera();
        return;
      }
      if (PermissionUtils.verifyPermissions(grantResults)) {
        target.getMusicFile();
      } else {
        if (!PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_GETMUSICFILE)) {
          target.showNeverAskForCamera();
        } else {
          target.showDeniedForCamera();
        }
      }
      break;
      default:
      break;
    }
  }

  private static final class GetMusicFilePermissionRequest implements PermissionRequest {
    private final WeakReference<SelectMusicActivity> weakTarget;

    private GetMusicFilePermissionRequest(SelectMusicActivity target) {
      this.weakTarget = new WeakReference<SelectMusicActivity>(target);
    }

    @Override
    public void proceed() {
      SelectMusicActivity target = weakTarget.get();
      if (target == null) return;
      ActivityCompat.requestPermissions(target, PERMISSION_GETMUSICFILE, REQUEST_GETMUSICFILE);
    }

    @Override
    public void cancel() {
      SelectMusicActivity target = weakTarget.get();
      if (target == null) return;
      target.showDeniedForCamera();
    }
  }
}
