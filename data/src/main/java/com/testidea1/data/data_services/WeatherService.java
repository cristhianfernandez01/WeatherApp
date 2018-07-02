package com.testidea1.data.data_services;

import com.testidea1.data.BuildConfig;
import com.testidea1.data.Endpoints;
import com.testidea1.data.data_services.request.DataService;
import com.testidea1.data.data_services.request.DataServiceGet;
import com.testidea1.data.helper.JsonSerializer;
import com.testidea1.data.model.WeatherDataResponse;
import com.testidea1.domain.core.Location;

import okhttp3.Response;

public class WeatherService{
    private DataService dataService;

    public WeatherDataResponse getWeatherByLocation(Location location){
        String url = Endpoints.WEATHER_ENDPOINT_API + BuildConfig.DARK_SKY_KEY + "/" + location.getLatitude()+","+location.getLongitude() + "?units=auto";
        dataService = new DataServiceGet();
        Response response = dataService.execute(url, "");
        return deserializeResponse(response);
    }

    public WeatherDataResponse deserializeResponse(Response response){
        try {
            if (response.isSuccessful()) {
                String json = response.body().string();
                WeatherDataResponse weatherResponse = JsonSerializer.jsonToEntity(json, WeatherDataResponse.class);

                return weatherResponse;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
