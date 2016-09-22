package info.davidjensen.entvex.allyourdatabasearebelongtouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    //typical statements that create and delete a table
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PlaceReaderContract.FeedEntry.TABLE_NAME + " (" +
                    PlaceReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    PlaceReaderContract.FeedEntry.COLUMN_NAME_TASK + TEXT_TYPE + COMMA_SEP +
                    PlaceReaderContract.FeedEntry.COLUMN_NAME_PLACE + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PlaceReaderContract.FeedEntry.TABLE_NAME;

    TaskReaderDbHelper taskReaderDbHelper;
    EditText eetTask  = (EditText) findViewById(R.id.eetTask);
    EditText eetPlace = (EditText) findViewById(R.id.eetPlace);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Making the database.
        taskReaderDbHelper = new TaskReaderDbHelper(getApplicationContext());
        taskReaderDbHelper.onCreate(taskReaderDbHelper.getWritableDatabase());

        Button btnAddTask = (Button) findViewById(R.id.btnAddTask);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Gets the data repository in write mode
                SQLiteDatabase db = taskReaderDbHelper.getWritableDatabase();


                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(PlaceReaderContract.FeedEntry.COLUMN_NAME_PLACE, eetTask.getText().toString());
                values.put(PlaceReaderContract.FeedEntry.COLUMN_NAME_TASK, eetPlace.getText().toString());

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(PlaceReaderContract.FeedEntry.TABLE_NAME, null, values);
            }
        });
    }

    public static final class PlaceReaderContract {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private PlaceReaderContract() {}

        /* Inner class that defines the table contents */
        public static class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "reminders";
            public static final String COLUMN_NAME_ID = "ID";
            public static final String COLUMN_NAME_TASK = "task_name";
            public static final String COLUMN_NAME_PLACE = "place_name";
        }
    }

    public class TaskReaderDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Task.db";

        public TaskReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}