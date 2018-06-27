package com.mehequanna.singlelocationapp.Listeners;

/**
 * This is used to handle communication between the CustomLocationListener
 * and LocationActivity.
 */
public interface LocationDataReceiver {
    void receiveLocationData(double latitude, double longitude, float accuracy, double altitude);
}
