package com.testidea1.weatherapp.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.testidea1.device.location.repository.LocationRepository;
import com.testidea1.domain.usecases.base.UseCaseHandler;
import com.testidea1.domain.usecases.weather.DetermineUserLocationUseCase;
import com.testidea1.weatherapp.presenter.use_case.UseCaseThreadPoolScheduler;

public class Injection {
    public static LocationRepository provideLocationRepository(@NonNull Context context) {
        return new LocationRepository(context);
    }

    public static DetermineUserLocationUseCase provideLocationUseCase(@NonNull Context context) {
        return new DetermineUserLocationUseCase(provideLocationRepository(context));
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseThreadPoolScheduler.getInstance();
    }
}
