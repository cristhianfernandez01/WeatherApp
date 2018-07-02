package com.testidea1.domain.usecases.weather;


import com.testidea1.domain.core.Location;
import com.testidea1.domain.repository.ILocationRepository;
import com.testidea1.domain.usecases.base.UseCase;

public class DetermineUserLocationUseCase extends UseCase<DetermineUserLocationUseCase.RequestValues, DetermineUserLocationUseCase.ResponseValue> {
    ILocationRepository repository;

    public DetermineUserLocationUseCase(ILocationRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        try {
            Location locationResult = repository.getCurrentUserLocation();
            getUseCaseCallback().onSuccess(new ResponseValue(locationResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final class RequestValues implements UseCase.RequestValues {

        public RequestValues() {
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private Location mLocation;

        public ResponseValue(Location currentLocationResult) {
            mLocation = currentLocationResult;
        }

        public Location getLocation() {
            return mLocation;
        }
    }
}
