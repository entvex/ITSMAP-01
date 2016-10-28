package info.davidjensen.entvex.assignment2weatherornotyoulikeit;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

public class WeatherService extends Service {

    private final IBinder iWeatherBinder = new WeatherBinder();
    public static String jsonUrl = "http://api.openweathermap.org/data/2.5/forecast/city?id=2624647&APPID=9c23a0dc8a8126b8b21eae4746205259";
    MySQLiteHelper mySQLiteHelper;

    Timer timer;
    int Interval = 30; //min
    int TimerInterval = 60*1000*Interval;

    public WeatherService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mySQLiteHelper = new MySQLiteHelper(getApplicationContext());

        new FetchWeatherData().execute();

        //Create a timer to run in a given interval.
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d("WeatherService", " run: getting data");
                new FetchWeatherData().execute();
            }
        }, TimerInterval);
    }

    @Override
    public IBinder onBind(Intent intent) {
        //Return the communication channel to the service.
        Log.d("WeatherService ", " Binding");
        return iWeatherBinder;
    }

    public class WeatherBinder extends Binder {
        WeatherService getService() {
            // Returns the instance of service so clients can call it's public methods
            Log.d("WeatherService ", "getService: return Service");
            return WeatherService.this;
        }
    }

    //Get the current Weather
    public WeatherInfo getCurrentWeather()
    {
        return mySQLiteHelper.getWeatherInfo(mySQLiteHelper.getAllWeatherInfo().size()-1);
    }

    //Query The database and get weather from the past 24h
    public List<WeatherInfo> getPastWeather()
    {
        List<WeatherInfo> weatherInfos = mySQLiteHelper.getAllWeatherInfo();
        weatherInfos.remove(weatherInfos.size()-1);
        return weatherInfos;
    }

    private WeatherInfo ParseJsonData(String WeatherJSON)
    {
        Log.d("WeatherService ", "ParseJsonData: parsing");
        try {

            WeatherInfo weatherInfo = new WeatherInfo();

            JSONObject reader = new JSONObject(WeatherJSON);
            JSONArray currentList = reader.getJSONArray("list");

            JSONObject current = currentList.getJSONObject(0);

            //Get Temperature
            JSONObject main = current.getJSONObject("main");
            weatherInfo.setTemperature(main.optDouble("temp") -273.15); // kelvin to deg

            //Get Weather Description
            JSONArray weatherArray = current.getJSONArray("weather");
            JSONObject weather = weatherArray.getJSONObject(0);
            weatherInfo.setWeatherDescription(weather.optString("description"));

            // Get Timestamp
            //weatherInfo.setTimestamp(current.optString("dt_txt"));
            weatherInfo.setTimestamp(timeStampConverter());

            // Get Icon ID
            weatherInfo.setWeatherIcon(weather.optString("icon"));

            return weatherInfo;

        } catch (JSONException e) {
            Log.d(TAG, "Error parsing");

            e.printStackTrace();
        }

        return null;
    }

    //http://www.codexpedia.com/android/asynctask-and-httpurlconnection-sample-in-android/ Thanks!
    private class FetchWeatherData extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/city?id=2624647&APPID=9c23a0dc8a8126b8b21eae4746205259");

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();
                return forecastJsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            WeatherInfo weatherInfo = ParseJsonData(s);
            if (weatherInfo != null)
            {
                mySQLiteHelper.addWeatherInfo(weatherInfo);
            }

            Intent broadIntent = new Intent();
            broadIntent.setAction(MainActivity.MESSAGE_SENT_ACTION);
            broadIntent.putExtra(MainActivity.MESSAGE_EXTRA, "New weather available");
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(broadIntent);

            Log.i("json", s);
        }
    }

    private String timeStampConverter(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date).toString();
    }
}
