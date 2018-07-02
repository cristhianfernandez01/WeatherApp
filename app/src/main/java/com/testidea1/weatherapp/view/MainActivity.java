package com.testidea1.weatherapp.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.testidea1.device.permission.Permission;
import com.testidea1.device.permission.PermissionAction;
import com.testidea1.domain.core.Location;
import com.testidea1.domain.core.Weather;
import com.testidea1.weatherapp.R;
import com.testidea1.weatherapp.injection.Injection;
import com.testidea1.weatherapp.presenter.MainPresenter;
import com.testidea1.weatherapp.utils.NumberFormatter;
import com.testidea1.weatherapp.utils.SnackBarUtil;
import com.testidea1.weatherapp.utils.dialog.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, MainPresenter.PermissionCallbacks {
    private MainPresenter presenter;
    protected View rationaleView;

    @BindView(R.id.city_field)
    TextView tvCityField;
    @BindView(R.id.current_temperature_field) TextView tvCurrentTemperature;
    @BindView(R.id.weather_icon)
    ImageView imIcon;
    @BindView(R.id.rain_field) TextView tvRain;
    @BindView(R.id.humidity_field) TextView tvHumidity;
    @BindView(R.id.summary_weather) TextView tvSummary;
    @BindView(R.id.btnUpdate)
    ImageButton btnUpdate;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(new PermissionAction(this), this, Injection.provideLocationUseCase(this));
        presenter.attachView(this);
        queryWeatherData();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryWeatherData();
            }
        });
    }

    @Override
    public void showProgressBar() {
        DialogUtils.showProgressDialog(this, null);
    }

    @Override
    public void hideProgressBar() {
        DialogUtils.getDialog().hide();
    }

    @Override
    public void queryWeatherData() {
        requestLocationUserPermission();
    }

    @Override
    public void showWeatherData(Weather weather) {
        tvCurrentTemperature.setText(NumberFormatter.formatTemperature(weather.getTemperature()));
        tvSummary.setText(weather.getSummary());
        tvHumidity.setText(NumberFormatter.formatPercent(weather.getHumidity(), 1));
        tvRain.setText(NumberFormatter.formatPercent(weather.getRain(), 1));
        determineIcon(weather.getIcon());
    }

    private void determineIcon(String icon) {
        switch (icon){
            case "clear-day":
                imIcon.setImageResource(R.drawable.clear_day);
                break;
            case "clear-night":
                imIcon.setImageResource(R.drawable.clear_night);
                break;
            case "partly-cloudy-day":
                imIcon.setImageResource(R.drawable.partly_cloudy_day);
                break;
            case "partly-cloudy-night":
                imIcon.setImageResource(R.drawable.partly_cloudy_night);
                break;
            case "cloudy":
                imIcon.setImageResource(R.drawable.cloudy);
                break;
            case "rain":
                imIcon.setImageResource(R.drawable.rain);
                break;
            case "sleet":
                imIcon.setImageResource(R.drawable.sleet);
                break;
            case "snow":
                imIcon.setImageResource(R.drawable.snow);
                break;
            case "wind":
                imIcon.setImageResource(R.drawable.wind);
                break;
            case "fog":
                imIcon.setImageResource(R.drawable.fog);
                break;

        }

    }

    @Override
    public void requestLocationUserPermission() {
        presenter.requestLocationPermission();
    }

    @Override
    public void setLocationResult(Location locationResult) {
        if (locationResult != null){
            tvCityField.setText(String.format(getString(R.string.location_city_format), locationResult.getCityName(), locationResult.getCountry()));
            presenter.getWeather(locationResult);
            return;
        }
        SnackBarUtil.showSnackbar(coordinatorLayout, getString(R.string.location_no_determine), Snackbar.LENGTH_LONG, this);
    }

    @Override
    public void showErrorMessage(String message) {
        SnackBarUtil.showSnackbarError(coordinatorLayout, message, Snackbar.LENGTH_LONG, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == Permission.LOCATION_FINE_PERMISSION){
                presenter.checkGrantedPermission(grantResults, requestCode);
        }
    }

    @Override
    public void permissionAccepted(int action) {
        switch (action) {
            case Permission.LOCATION_FINE_PERMISSION:
                presenter.getLocationForUser();
                break;
        }
    }

    @Override
    public void permissionDenied(int action) {
        if (dismissPermissionRationale() == 0 && action == Permission.LOCATION_FINE_PERMISSION) {
            showMessagePermissionDenied();
        }
    }

    private void showMessagePermissionDenied() {
        SnackBarUtil.showSnackbar(coordinatorLayout, getString(R.string.location_permmission_denied), Snackbar.LENGTH_LONG, this);
    }

    @Override
    public void showRationale(int action) {
        switch (action) {
            case Permission.LOCATION_FINE_PERMISSION:
                createAndShowPermissionRationale(action, R.string.rationale_get_location_title, R.string
                        .rationale_get_location_subtitle);
                break;
        }
    }

    protected void createAndShowPermissionRationale(int action, int titleResId, int subtitleResId) {
        if (rationaleView == null) {
            rationaleView = ((ViewStub) findViewById(R.id.permission_rationale_stub)).inflate();
        } else {
            rationaleView.setVisibility(View.VISIBLE);
        }
        ((TextView) rationaleView.findViewById(R.id.rationale_title)).setText(titleResId);
        ((TextView) rationaleView.findViewById(R.id.rationale_subtitle)).setText(subtitleResId);
        rationaleView.setTag(action);
    }

    public void onAcceptRationaleClick(View view) {
        int action = dismissPermissionRationale();
        switch (action) {
            case Permission.LOCATION_FINE_PERMISSION:
                presenter.requestLocationPermissionAfterRationale();
                break;
        }
    }

    protected int dismissPermissionRationale() {
        if (rationaleView != null && rationaleView.getVisibility() == View.VISIBLE) {
            rationaleView.setVisibility(View.GONE);
            return (int) rationaleView.getTag();
        }
        return 0;
    }
}
