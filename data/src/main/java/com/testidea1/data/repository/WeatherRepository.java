package com.testidea1.data.repository;

import com.testidea1.data.data_services.WeatherService;
import com.testidea1.data.model.WeatherDataResponse;
import com.testidea1.domain.core.Location;
import com.testidea1.domain.core.Weather;
import com.testidea1.domain.repository.IWeatherRepository;

import java.util.Date;

public class WeatherRepository implements IWeatherRepository
{
    private WeatherService service;

    public WeatherRepository(){
        service = new WeatherService();
    }

    public void setService(WeatherService service) {
        this.service = service;
    }

    @Override
    public Weather getWeatherByLocation(Location location) throws Exception {
        if (location != null){
            WeatherDataResponse dataResponse = service.getWeatherByLocation(location);
            if (dataResponse != null){
                Weather weather = new Weather();
                weather.setDate(new Date(dataResponse.getCurrently().getTime()));
                weather.setHumidity(dataResponse.getCurrently().getHumidity());
                weather.setIcon(dataResponse.getCurrently().getIcon());
                weather.setLastUpdated(new Date().toString());
                weather.setSummary(dataResponse.getCurrently().getSummary());
                weather.setTemperature(dataResponse.getCurrently().getTemperature());
                weather.setPressure(dataResponse.getCurrently().getPressure());
                weather.setRain(dataResponse.getCurrently().getPrecipProbability());

                return weather;
            }
        }
        return null;
    }
}
