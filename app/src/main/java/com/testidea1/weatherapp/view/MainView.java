package com.testidea1.weatherapp.view;

import com.testidea1.domain.core.Location;
import com.testidea1.domain.core.Weather;

public interface MainView {
    void showProgressBar();

    void hideProgressBar();

    void queryWeatherData();

    void showWeatherData(Weather weather);

    void requestLocationUserPermission();

    void setLocationResult(Location locationResult);

    void showErrorMessage(String message);
}
