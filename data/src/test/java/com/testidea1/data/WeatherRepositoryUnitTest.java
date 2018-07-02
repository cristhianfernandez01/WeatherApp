package com.testidea1.data;

import com.testidea1.data.data_services.WeatherService;
import com.testidea1.data.model.CurrentlyWeather;
import com.testidea1.data.model.WeatherDataResponse;
import com.testidea1.data.repository.WeatherRepository;
import com.testidea1.domain.core.Location;
import com.testidea1.domain.core.Weather;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class WeatherRepositoryUnitTest {
    @Mock
    WeatherRepository mSubject;

    @Mock
    WeatherService mService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mSubject = new WeatherRepository();
        mSubject.setService(mService);
        when(mService.getWeatherByLocation(any(Location.class))).thenReturn(getExmapleWeatherDataResponse());
    }

    @Test
    public void check_getWeatherdata() throws Exception {
        Location location = getLocationExample();

        Weather weather = mSubject.getWeatherByLocation(location);
        assertNotEquals("Respuesta no formateada correctamente",null, weather);
        assertEquals(getExmapleWeatherDataResponse().getCurrently().getTemperature(), weather.getTemperature(), 0.0);
    }

    private WeatherDataResponse getExmapleWeatherDataResponse(){
        WeatherDataResponse response = new WeatherDataResponse();
        response.setLatitude(getLocationExample().getLatitude());
        response.setLongitude(getLocationExample().getLongitude());
        response.setTimezone(Calendar.getInstance().getTimeZone().getID());
        response.setCurrently(
                new CurrentlyWeather(Calendar.getInstance().getTime().getTime(),
                        "Overcloud", "cloudy", 0,15, 15, 0.6, 1, 12));
        return response;
    }

    public Location getLocationExample(){
        return new Location(-25.55, -19.44);
    }
}