package com.testidea1.weatherapp.utils;

import java.text.DecimalFormat;

public class NumberFormatter {

    public static String formatPercent(double done, int digits) {
        DecimalFormat percentFormat = new DecimalFormat("0.0%");
        percentFormat.setDecimalSeparatorAlwaysShown(false);
        percentFormat.setMinimumFractionDigits(digits);
        percentFormat.setMaximumFractionDigits(digits);
        return percentFormat.format(done);
    }

    public static String formatTemperature(double number){
        DecimalFormat percentFormat = new DecimalFormat("0.0 ºC");
        percentFormat.setDecimalSeparatorAlwaysShown(false);
        percentFormat.setMinimumFractionDigits(0);
        percentFormat.setMaximumFractionDigits(0);
        return percentFormat.format(number);
    }
}
