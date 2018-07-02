package com.testidea1.device.permission;

public interface IPermissionAction {
    boolean hasSelfPermission(String permission);

    void requestPermission(String permission, int requestCode);

    boolean shouldShowRequestPermissionRationale(String permission);
}
