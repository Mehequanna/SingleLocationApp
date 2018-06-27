package com.mehequanna.singlelocationapp.Listeners;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.mehequanna.singlelocationapp.Activities.LocationActivity;

/**
 * This listener is used to handle the location information requested by the Location Manager.
 */
public class CustomLocationListener implements LocationListener {
    private LocationActivity activity;
    private final String simpleClassName = this.getClass().getSimpleName();

    public void setActivity(LocationActivity locationActivity) {
        activity = locationActivity;
    }

    @Override
    public void onLocationChanged(Location location) {
        String provider = location.getProvider();
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        float accuracy = location.getAccuracy();
        long time = location.getTime();
        double altitude = location.getAltitude();

        // TODO: Replace with receiver.
        if (activity != null) {
            activity.setLocation(latitude, longitude, accuracy, altitude);
        }

        String logMessage =
                "Provider Name: " + provider +
                ". Latitude: " + latitude +
                ". Longitude: " + longitude +
                ". Accuracy: " + accuracy +
                ". Time: " + time +
                ". Altitude: " + altitude + ".";

        Log.i(simpleClassName, "onLocationChanged: " + logMessage);
    }

    // Called when provider status is changed.
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i(simpleClassName, "onStatusChanged: " + provider);
    }

    // Called when user enables provider.
    @Override
    public void onProviderEnabled(String provider) {
        Log.i(simpleClassName, "onProviderEnabled: " + provider);
    }

    // Called when user disables provider.
    @Override
    public void onProviderDisabled(String provider) {
        Log.i(simpleClassName, "onProviderDisabled: " + provider);
    }
}
