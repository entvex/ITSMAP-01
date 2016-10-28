package info.davidjensen.entvex.assignment2weatherornotyoulikeit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bagger on 30-09-2016.
 */

public class WeatherAdapter extends BaseAdapter {

    Context context;
    List<WeatherInfo> weatherInfoList;
    WeatherInfo weatherInfo;

    public WeatherAdapter(Context context, List<WeatherInfo> weatherInfoList)
    {
        this.context   = context;
        this.weatherInfoList = weatherInfoList;
    }

    @Override
    public int getCount() {
        return weatherInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        if (weatherInfoList !=null)
        {
            return weatherInfoList.get(i);
        } else {
            return 0;
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null )
        {
            LayoutInflater InflaterReminder = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = InflaterReminder.inflate(R.layout.weather_list_item,null);
        }
        weatherInfo = weatherInfoList.get(position);

        //Filling a item
        if(weatherInfo != null)
        {
            ImageView weatherIcon = (ImageView)view.findViewById(R.id.imgWeatherIcon);
            String variableValue = "a"+weatherInfo.getWeatherIcon();
            weatherIcon.setImageResource(context.getResources().getIdentifier(variableValue, "drawable", context.getPackageName()));

            TextView weatherDescription = (TextView)view.findViewById(R.id.ttvDescription);
            weatherDescription.setText(weatherInfo.getWeatherDescription());

            TextView weatherDate = (TextView) view.findViewById(R.id.ttvDate);
            weatherDate.setText(weatherInfo.getDate());

            TextView weatherTemperature = (TextView)view.findViewById(R.id.ttvTemperature);
            weatherTemperature.setText(weatherInfo.getTemperature().toString().substring(0,2) + "\u2103");

            TextView weatherTime = (TextView)view.findViewById(R.id.ttvTime);
            weatherTime.setText(weatherInfo.getTime());
        }
        return view;
    }
}