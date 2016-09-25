package info.davidjensen.entvex.allyourdatabasearebelongtouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by entvex on 24-09-2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "reminderDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create the table
        String CREATE_REMINDER_TABLE = "CREATE TABLE reminders ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "task_name TEXT, "+
                "place_name TEXT )";

        db.execSQL(CREATE_REMINDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table if it is there.
        db.execSQL("DROP TABLE IF EXISTS reminders");

        // create fresh reminders table
        this.onCreate(db);
    }


    // reminders table name
    private static final String TABLE_REMINDERS = "reminders";

    // reminder Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TASK = "task_name";
    private static final String KEY_PLACE = "place_name";

    private static final String[] COLUMNS = {KEY_ID, KEY_TASK, KEY_PLACE};

    public void addReminder(Reminder reminder){
        Log.d("addReminder", reminder.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TASK, reminder.getTask_name()); // get task
        values.put(KEY_PLACE, reminder.getPlace_name()); // get place

        // 3. insert
        db.insert(TABLE_REMINDERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Reminder getTask(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        //public Cursor query (boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
        //build query
        Cursor cursor =
                db.query(TABLE_REMINDERS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        //build object
        Reminder reminder = new Reminder();
        reminder.setId(Integer.parseInt(cursor.getString(0)));
        reminder.setTask_name(cursor.getString(1));
        reminder.setPlace_name(cursor.getString(2));

        Log.d("getTask("+id+")", reminder.toString());

        return reminder;
    }

    public List<Reminder> getAllTasks() {
        List<Reminder> reminders = new LinkedList<Reminder>();

        String query = "SELECT  * FROM " + TABLE_REMINDERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // Loop over all the rows and get the data.
        Reminder reminder = null;
        if (cursor.moveToFirst()) {
            do {
                reminder = new Reminder();
                reminder.setId(Integer.parseInt(cursor.getString(0)));
                reminder.setTask_name(cursor.getString(1));
                reminder.setPlace_name(cursor.getString(2));

                reminders.add(reminder);
            } while (cursor.moveToNext());
        }

        Log.d("getAllTasks()", reminders.toString());

        return reminders;
    }

    public int updateTask(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("task_name", reminder.getTask_name());
        values.put("place_name", reminder.getPlace_name());

        int i = db.update(TABLE_REMINDERS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(reminder.getId()) }); //selection args

        db.close();

        return i;
    }

    public void deleteTask(Reminder reminder) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_REMINDERS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(reminder.getId()) });

        db.close();

        Log.d("deleteTask", reminder.toString());
    }
}