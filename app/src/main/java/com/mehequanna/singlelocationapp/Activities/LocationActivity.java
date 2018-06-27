package com.mehequanna.singlelocationapp.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.mehequanna.singlelocationapp.Listeners.CustomLocationListener;
import com.mehequanna.singlelocationapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mehequanna.singlelocationapp.R.id.get_location_button;


public class LocationActivity extends AppCompatActivity {
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 12;

    @BindView(R.id.accuracy_text_view) TextView accuracyTextView;
    @BindView(R.id.altitude_text_view) TextView altitudeTextView;
    @BindView(R.id.longitude_text_view) TextView longitudeTextView;
    @BindView(R.id.latitude_text_view) TextView latitudeTextView;

    CustomLocationListener gpsListener;
    private final String simpleClassName = this.getClass().getSimpleName();

    // This field is used to keep track of whether the user has
    private boolean permissionGranted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        permissionGranted = false;

        // Set up gpsListener
        // TODO Use singleton?
        gpsListener = new CustomLocationListener();
        gpsListener.setActivity(this);
    }

    /**
     * Checks if the application has permission to access the devices fine location.
     * If permission is denied, no location information will be requested.
     * If permission is accepted, the location information will be requested from the location manager.
     */
    @OnClick(get_location_button)
    public void getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            permissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, MY_PERMISSION_ACCESS_FINE_LOCATION);
            permissionGranted = false;
        }

        if (permissionGranted) {
            if (locationManager != null) {
                // The information returned by this method is handled by the CustomLocationListener class.
                locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, gpsListener, null);
            } else {
                Toast.makeText(this, "Unable to find location", Toast.LENGTH_SHORT).show();
                Log.e(simpleClassName, "Location manager not instantiated.");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionGranted = true;
            getLocation();
            Log.i(simpleClassName, "onRequestPermissionsResult: Accepted");
        } else {
            permissionGranted = false;
            Log.i(simpleClassName, "onRequestPermissionsResult: Denied");
        }
    }

    /**
     * Takes the location data and updates the textViews shown to the user.
     *
     * @param latitude double
     * @param longitude double
     * @param accuracy float
     * @param altitude double
     */
    public void setLocation(double latitude, double longitude, float accuracy, double altitude) {
        String latitudeText = "Latitude: " + String.valueOf(latitude);
        String longitudeText = "Longitude: " + String.valueOf(longitude);
        String altitudeText = "Altitude in meters: " + String.valueOf(altitude);
        String accuracyText = "Accuracy in meters: " + String.valueOf(accuracy);

        latitudeTextView.setText(latitudeText);
        longitudeTextView.setText(longitudeText);
        altitudeTextView.setText(altitudeText);
        accuracyTextView.setText(accuracyText);
    }
}
