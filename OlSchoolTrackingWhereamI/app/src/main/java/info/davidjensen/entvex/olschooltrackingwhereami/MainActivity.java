package info.davidjensen.entvex.olschooltrackingwhereami;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    TextView ttvLocationInfo;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Managers
        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        //GUI
        ttvLocationInfo = (TextView) findViewById(R.id.ttvLocationInfo);
        Button btnStartTracking = (Button) findViewById(R.id.btnStartTracking);
        Button btnStopTracking = (Button) findViewById(R.id.btnStopTracking);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                ttvLocationInfo.setText(location.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        btnStartTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (perms()) return;

                LocationProvider locationProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);

                if (locationProvider != null) {
                    try {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    } catch (SecurityException e) {
                    }
                } else {
                    ttvLocationInfo.setText("No gps location is available");
                }
            }
        });
        
        btnStopTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    locationManager.removeUpdates(locationListener);
                    ttvLocationInfo.setText("Stopped");
                }
                catch (SecurityException e)
                {

                }
            }
        });
    }

    private boolean perms() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return true;
        }
        return false;
    }

}