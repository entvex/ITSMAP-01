package info.davidjensen.entvex.jsonbourne;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    TextView ttvType;
    EditText dump;

    String testJSON = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCheckConnectivity = (Button)   findViewById(R.id.btnCheckConnectivity);
        Button btnGetAarhusWeather  = (Button)   findViewById(R.id.btnGetAarhusWeather);
        Button btnJSON              = (Button)   findViewById(R.id.btnJSON);

        dump = (EditText) findViewById(R.id.ettJsonDump);
        ttvType = (TextView) findViewById(R.id.ttvType);

        btnCheckConnectivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected())
                {
                    Toast.makeText(getApplicationContext(),"Network is avaliable and it can handle data!",Toast.LENGTH_LONG);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Network is NOT avaliable",Toast.LENGTH_LONG);
                }

                ttvType.setText(networkInfo.getTypeName());
            }
        });

        btnGetAarhusWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    new FetchWeatherData().execute();
            }
        });

        btnJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!testJSON.equals("") || testJSON != null) {
                    Log.d("jsonknap", "onClick: ");
                    try {
                        JSONObject reader = new JSONObject(testJSON);
                        JSONArray currentList = reader.getJSONArray("list");
                        JSONObject current = currentList.getJSONObject(0);

                        JSONObject main = current.getJSONObject("main");
                        Double Temperature = main.optDouble("temp") - 273.15;

                        Toast.makeText(getApplicationContext(), "Is is " + String.valueOf(Temperature) + " D", Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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
            testJSON = s;
            dump.setText(s);
            Log.i("json", s);
        }
    }



}
