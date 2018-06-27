package com.mehequanna.singlelocationapp;

import com.mehequanna.singlelocationapp.Activities.LocationActivity;
import com.mehequanna.singlelocationapp.Listeners.CustomLocationListener;
import com.mehequanna.singlelocationapp.Listeners.LocationDataReceiver;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomLocationListenerUnitTest {
    @Test
    public void singleton_returnsSameInstance() {
        LocationActivity locationActivity = new LocationActivity();
        CustomLocationListener locationListener1 = locationActivity.getLocationListener();
        CustomLocationListener locationListener2 = locationActivity.getLocationListener();

        assertTrue(locationListener1.equals(locationListener2));
    }

    @Test
    public void singleton_returnsDifferentInstance() {
        LocationActivity locationActivity = new LocationActivity();
        CustomLocationListener locationListener1 = locationActivity.getLocationListener();
        CustomLocationListener locationListener2 = new CustomLocationListener(new LocationDataReceiver() {
            @Override
            public void receiveLocationData(double latitude, double longitude, float accuracy, double altitude) {

            }
        });

        assertFalse(locationListener1.equals(locationListener2));
    }
}