package info.davidjensen.entvex.assignment2weatherornotyoulikeit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by entvex on 26-09-2016.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "WeatherDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create the table
        String CREATE_WEATHER_TABLE = "CREATE TABLE weather ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "DESC TEXT, "+
                "TEMP TEXT, "+
                "Timestamp TEXT, "+
                "ICON TEXT)";

        db.execSQL(CREATE_WEATHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table if it is there.
        db.execSQL("DROP TABLE IF EXISTS weather");

        this.onCreate(db);
    }


    // weather table name
    private static final String TABLE_WEATHER = "weather";

    // weather Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DESC = "DESC";
    private static final String KEY_TEMP = "TEMP";
    private static final String KEY_DATE = "Timestamp";
    private static final String KEY_ICON = "ICON";

    private static final String[] COLUMNS = {KEY_ID, KEY_DESC, KEY_TEMP, KEY_DATE, KEY_ICON};

    public void addWeatherInfo(WeatherInfo weatherInfo){
        Log.d("addWeahterInfo", weatherInfo.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DESC, weatherInfo.getWeatherDescription());
        values.put(KEY_TEMP, weatherInfo.getTemperature());
        values.put(KEY_DATE, weatherInfo.getTimestamp());
        values.put(KEY_ICON, weatherInfo.getWeatherIcon());

        db.insert(TABLE_WEATHER, null, values);
        db.close();

        cleanUp();
    }

    public WeatherInfo getWeatherInfo(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_WEATHER, COLUMNS, " id = ?", new String[] { String.valueOf(id) },
                        null,
                        null,
                        null,
                        null);

        if (cursor != null)
            cursor.moveToFirst();

        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setID(Integer.parseInt(cursor.getString(0)));
        weatherInfo.setWeatherDescription(cursor.getString(1));
        weatherInfo.setTemperature(cursor.getDouble(2));
        weatherInfo.setTimestamp(cursor.getString(3));
        weatherInfo.setWeatherIcon(cursor.getString(4));

        Log.d("getWeatherInfo("+id+")", weatherInfo.toString());

        return weatherInfo;
    }

    public List<WeatherInfo> getAllWeatherInfo() {
        List<WeatherInfo> weatherInfos = new LinkedList<WeatherInfo>();

        String query = "SELECT  * FROM " + TABLE_WEATHER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // Loop over all the rows and get the data.
        WeatherInfo weatherInfo = null;
        if (cursor.moveToFirst()) {
            do {
                weatherInfo = new WeatherInfo();
                weatherInfo.setID(Integer.parseInt(cursor.getString(0)));
                weatherInfo.setWeatherDescription(cursor.getString(1));
                weatherInfo.setTemperature(cursor.getDouble(2));
                weatherInfo.setTimestamp(cursor.getString(3));
                weatherInfo.setWeatherIcon(cursor.getString(4));

                weatherInfos.add(weatherInfo);
            } while (cursor.moveToNext());
        }

        Log.d("getAllWeatherInfo()", weatherInfos.toString());

        return weatherInfos;
    }

    private void cleanUp(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_WEATHER + " WHERE " + KEY_DATE + " <= date('now','-1 day')";

        db.rawQuery(query, null);
        db.close();

        Log.d("cleanUp", "Cleaned up old data");
    }
}