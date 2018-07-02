package com.testidea1.data.model;

public class WeatherDataResponse {
    private double latitude;
    private double longitude;
    private String timezone;
    private CurrentlyWeather currently;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public CurrentlyWeather getCurrently() {
        return currently;
    }

    public void setCurrently(CurrentlyWeather currently) {
        this.currently = currently;
    }
}
