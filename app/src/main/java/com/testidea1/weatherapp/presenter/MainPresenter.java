package com.testidea1.weatherapp.presenter;

import android.Manifest;
import android.content.pm.PackageManager;

import com.testidea1.data.repository.WeatherRepository;
import com.testidea1.device.permission.IPermissionAction;
import com.testidea1.device.permission.Permission;
import com.testidea1.device.permission.PermissionAction;
import com.testidea1.domain.core.Location;
import com.testidea1.domain.usecases.base.UseCase;
import com.testidea1.domain.usecases.base.UseCaseHandler;
import com.testidea1.domain.usecases.weather.DetermineUserLocationUseCase;
import com.testidea1.domain.usecases.weather.SearchWeatherByLocationUseCase;
import com.testidea1.weatherapp.injection.Injection;
import com.testidea1.weatherapp.view.MainView;

public class MainPresenter{
    private IPermissionAction permissionAction;
    private PermissionCallbacks permissionCallbacks;
    private DetermineUserLocationUseCase locationDetermineUseCase;
    private SearchWeatherByLocationUseCase searchWeatherByLocationUseCase;
    private MainView mMainView;
    private UseCaseHandler mUseCaseHandler;

    public MainPresenter(PermissionAction permissionAction, PermissionCallbacks permissionCallbacks, DetermineUserLocationUseCase locationUseCase) {
        this.permissionAction = permissionAction;
        this.permissionCallbacks = permissionCallbacks;
        mUseCaseHandler = Injection.provideUseCaseHandler();
        locationDetermineUseCase = locationUseCase;
        searchWeatherByLocationUseCase = new SearchWeatherByLocationUseCase(new WeatherRepository());
    }

    public void attachView(MainView view){
        this.mMainView = view;
    }

    public void requestLocationPermission() {
        checkAndRequestPermission(Permission.LOCATION_FINE_PERMISSION, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public void requestLocationPermissionAfterRationale() {
        requestPermission(Permission.LOCATION_FINE_PERMISSION, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public void getLocationForUser(){
        mUseCaseHandler.execute(locationDetermineUseCase, new DetermineUserLocationUseCase.RequestValues(),
                new UseCase.UseCaseCallback<DetermineUserLocationUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(DetermineUserLocationUseCase.ResponseValue response) {
                        mMainView.setLocationResult(response.getLocation());
                    }

                    @Override
                    public void onError() {
                        mMainView.showErrorMessage("Se produjo un error al intentar obtener la ubicacion.");
                    }
                });
    }

    public void getWeather(Location location){
        mMainView.showProgressBar();
        mUseCaseHandler.execute(searchWeatherByLocationUseCase, new SearchWeatherByLocationUseCase.RequestValues(location),
                new UseCase.UseCaseCallback<SearchWeatherByLocationUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(SearchWeatherByLocationUseCase.ResponseValue response) {
                        mMainView.hideProgressBar();
                        mMainView.showWeatherData(response.getResultWeather());
                    }

                    @Override
                    public void onError() {
                        mMainView.hideProgressBar();
                        mMainView.showErrorMessage("Se produjo un error al intentar obtener la informacion del tiempo.");
                    }
                });
    }

    private void checkAndRequestPermission(int permissionCode, String permissionName) {
        if (permissionAction.hasSelfPermission(permissionName)) {
            permissionCallbacks.permissionAccepted(permissionCode);
        } else {
            if (permissionAction.shouldShowRequestPermissionRationale(permissionName)) {
                permissionCallbacks.showRationale(permissionCode);
            } else {
                permissionAction.requestPermission(permissionName, permissionCode);
            }
        }
    }

    private void requestPermission(int permissionCode, String permissionName) {
        permissionAction.requestPermission(permissionName, permissionCode);
    }

    public interface PermissionCallbacks {
        void permissionAccepted(int actionCode);

        void permissionDenied(int actionCode);

        void showRationale(int actionCode);
    }

    public void checkGrantedPermission(int[] grantResults, int requestCode) {
        if (verifyGrantedPermission(grantResults)) {
            permissionCallbacks.permissionAccepted(requestCode);
        } else {
            permissionCallbacks.permissionDenied(requestCode);
        }
    }

    private boolean verifyGrantedPermission(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
