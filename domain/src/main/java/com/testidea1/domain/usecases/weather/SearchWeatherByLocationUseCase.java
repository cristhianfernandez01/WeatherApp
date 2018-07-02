package com.testidea1.domain.usecases.weather;


import com.testidea1.domain.core.Location;
import com.testidea1.domain.core.Weather;
import com.testidea1.domain.repository.IWeatherRepository;
import com.testidea1.domain.usecases.base.UseCase;

public class SearchWeatherByLocationUseCase extends UseCase<SearchWeatherByLocationUseCase.RequestValues, SearchWeatherByLocationUseCase.ResponseValue> {
    IWeatherRepository repository;

    public SearchWeatherByLocationUseCase(IWeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        try {
            Weather storesResult = repository.getWeatherByLocation(requestValues.getLocation());
            getUseCaseCallback().onSuccess(new ResponseValue(storesResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final Location mLocation;

        public RequestValues(Location term) {
            mLocation = term;
        }

        public Location getLocation() {
            return mLocation;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private Weather mResultWeather;

        public ResponseValue(Weather resultsWeather) {
            mResultWeather = resultsWeather;
        }

        public Weather getResultWeather() {
            return mResultWeather;
        }
    }
}
