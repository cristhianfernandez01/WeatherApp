package com.testidea1.device.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


public class PermissionAction implements IPermissionAction {

    private Activity activity;

    public PermissionAction(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean hasSelfPermission(String permission) {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void requestPermission(String permission, int requestCode) {
        ActivityCompat.requestPermissions(activity, new String[] { permission }, requestCode);
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }
}