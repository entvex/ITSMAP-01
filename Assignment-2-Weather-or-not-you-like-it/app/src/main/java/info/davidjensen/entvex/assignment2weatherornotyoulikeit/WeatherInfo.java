package info.davidjensen.entvex.assignment2weatherornotyoulikeit;

/**
 * Created by entvex on 22-09-2016.
 */

public class WeatherInfo {
    private int ID;
    private Double Temperature;
    private String WeatherDescription;
    private String WeatherIcon;
    private String Timestamp;
    private String Time;
    private String Date;

    public void WeatherInfo( double Temperature, String WeatherDescription, String Timestamp, String WeatherIcon )
    {
        this.Temperature = Temperature;
        this.WeatherDescription = WeatherDescription;
        this.WeatherIcon = WeatherIcon;
        setTimestamp(Timestamp);
    }

    public int getID() {
        return this.ID;
    }

    public Double getTemperature() {
        return this.Temperature;
    }

    public String getWeatherDescription() {
        return this.WeatherDescription;
    }

    public String getWeatherIcon(){
        return  this.WeatherIcon;
    }

    public String getTime(){
        return this.Time;
    }

    public String getDate() {
        return this.Date;
    }

    public String getTimestamp() {
        return this.Timestamp;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTemperature(Double temperature) {
        Temperature = temperature;
    }

    public void setTimestamp(String timestamp) {
        this.Timestamp = timestamp;
        String TimeStamp[] = this.Timestamp.split("\\s+");
        this.Date = TimeStamp[0];
        this.Time = TimeStamp[1];
    }

    public void setWeatherDescription(String weatherDescription) {
        WeatherDescription = weatherDescription;
    }

    public void setWeatherIcon(String weatherIcon){
        this.WeatherIcon = weatherIcon;
    }
}
