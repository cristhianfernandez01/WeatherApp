package com.testidea1.device.utils;

import android.os.Build;

public class CommonUtils {

    public static boolean isMarshmallowOrHigher() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false;
        } else {
            return true;
        }
    }
}