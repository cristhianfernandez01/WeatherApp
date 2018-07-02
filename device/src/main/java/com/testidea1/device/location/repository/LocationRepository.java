package com.testidea1.device.location.repository;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.testidea1.domain.core.Location;
import com.testidea1.domain.repository.ILocationRepository;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationRepository implements ILocationRepository {
    private Context mContext;
    private LocationManager mLocationManager;
    private Geocoder mGeocoder;

    public LocationRepository(Context mContext) {
        this.mContext = mContext;
        this.mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        this.mGeocoder = new Geocoder(mContext, Locale.getDefault());
    }

    @Override
    public Location getCurrentUserLocation() {
        android.location.Location location = getLastKnownLocation();
        if (location != null) {
            Address address = getAddressForCurrentLocation(location);
            return new Location(location.getLatitude(), location.getLongitude(), address.getLocality(), address.getCountryCode());
        }
        return null;
    }

    private android.location.Location getLastKnownLocation() {
        List<String> providers = mLocationManager.getProviders(true);
        android.location.Location bestLocation = null;
        if (ActivityCompat.checkSelfPermission(this.mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            for (String provider : providers) {
                android.location.Location l = mLocationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    bestLocation = l;
                }
            }
        }

        return bestLocation;
    }

    private Address getAddressForCurrentLocation(android.location.Location location) {
        List<Address> addresses = null;
        try {
            addresses = mGeocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1);
            if (addresses != null && addresses.size() > 0)
                return addresses.get(0);
        } catch (IOException ioException) {
            Log.e("Geocoder", "Error al acceder a la Geolocalizacion", ioException);
        }
        return null;
    }
}
