package com.testidea1.domain.repository;

import com.testidea1.domain.core.Location;
import com.testidea1.domain.core.Weather;

public interface IWeatherRepository {
    Weather getWeatherByLocation(Location location) throws Exception;
}
