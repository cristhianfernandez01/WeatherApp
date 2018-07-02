package com.testidea1.weatherapp.utils;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.testidea1.weatherapp.R;

public class SnackBarUtil {

    public static void showSnackbar(View view, String message, int duration, Activity activity)
    {
        Snackbar snackbar = Snackbar
                .make(view, message, duration);
        View snackView = snackbar.getView();
        snackView.setBackgroundColor(ContextCompat.getColor(activity, R.color.classic_colorPrimary));
        TextView snackViewText = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
        snackViewText.setTextColor(ContextCompat.getColor(activity, R.color.classic_colorTintedBackground));
        snackbar.show();
    }

    public static void showSnackbarError(View view, String message, int duration, Activity activity)
    {
        Snackbar snackbar = Snackbar
                .make(view, message, duration);
        View snackView = snackbar.getView();
        snackView.setBackgroundColor(ContextCompat.getColor(activity, R.color.light_red));
        TextView snackViewText = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
        snackViewText.setTextColor(ContextCompat.getColor(activity, R.color.classic_colorTintedBackground));
        snackbar.show();
    }
}
