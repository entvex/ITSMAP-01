package info.davidjensen.entvex.assignment2weatherornotyoulikeit;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_SENT_ACTION = "ListenToMe";
    public static final String MESSAGE_EXTRA = "NewWeatherData";

    WeatherService mService;
    boolean mBound = false;
    WeatherAdapter weatherAdapter;
    ListView PastWeather;
    TextView currentDescription, currentTemperature;
    ImageView currentIcon;
    FloatingActionButton refreshBtn;
    BroadcastReceiver broadReciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //OnCreateStartService
        Intent intent = new Intent(MainActivity.this,WeatherService.class);
        startService(intent);

        currentDescription = (TextView)findViewById(R.id.ttvDescriptionCurrent);
        currentTemperature = (TextView)findViewById(R.id.ttvTemperatureCurrent);
        currentIcon = (ImageView)findViewById(R.id.imgIconCurrent);

        broadReciever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateWeatherInfo();
            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(broadReciever, new IntentFilter(MESSAGE_SENT_ACTION));

        refreshBtn = (FloatingActionButton) findViewById(R.id.fabRefresh);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound) {
                    weatherAdapter = new WeatherAdapter(getApplicationContext(), mService.getPastWeather());
                    PastWeather = (ListView)findViewById(R.id.ltvPastWeather);
                    PastWeather.setAdapter(weatherAdapter);

                    updateWeatherInfo();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Unbind from the service
        if (mBound) {
            Toast.makeText(getApplicationContext(), "Unbinding from service", Toast.LENGTH_SHORT).show();
            unbindService(mConnection);
            mBound = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to WeatherService
        Toast.makeText(getApplicationContext(), "Binding to service", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, WeatherService.class);
        bindService(intent, mConnection, getApplicationContext().BIND_AUTO_CREATE);
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            WeatherService.WeatherBinder binder = (WeatherService.WeatherBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    private void updateWeatherInfo(){
        WeatherInfo currentWeatherInfo =  mService.getCurrentWeather();

        currentDescription.setText(currentWeatherInfo.getWeatherDescription());
        currentTemperature.setText(currentWeatherInfo.getTemperature().toString() + "\u2103");

        String variableValue = "a"+currentWeatherInfo.getWeatherIcon();
        currentIcon.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));

    }

}