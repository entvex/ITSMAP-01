package info.davidjensen.entvex.mementowhome;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String LastName = "nameLastKey";
    public static final String Age = "nameAge";
    public static final String Phone = "phoneKey";

    EditText eetPhone;
    EditText eetAge;
    EditText eetLastName;
    EditText eetFirstName;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eetFirstName = (EditText) findViewById(R.id.eetName);
        eetLastName = (EditText) findViewById(R.id.eetLastName);
        eetAge = (EditText) findViewById(R.id.eetAge);
        eetPhone = (EditText) findViewById(R.id.eetPhone);

        sharedPref = getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        eetFirstName.setText(sharedPref.getString(Name,""));
        eetLastName.setText(sharedPref.getString(LastName,""));
        eetAge.setText(sharedPref.getString(Age,""));
        eetPhone.setText(sharedPref.getString(Phone,""));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Event"," Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Event"," Stop");
        sharedPref = getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        /*
        sharedPref.edit().putString(Name,eetFirstName.getText().toString()).commit();
        sharedPref.edit().putString(Age,eetAge.getText().toString()).commit();
        sharedPref.edit().putString(LastName,eetLastName.getText().toString()).commit();
        sharedPref.edit().putString(Phone,eetPhone.getText().toString()).commit();*/

        //NOTEE ABOUT APPLY AND COMMIT
        //Unlike commit(), which writes its preferences out to persistent storage synchronously, apply() commits its changes to the in-memory SharedPreferences immediately but starts an asynchronous commit to disk and you won't be notified of any failures.
        // If another editor on this SharedPreferences does a regular commit() while a apply() is still outstanding, the commit()
        // will block until all async commits are completed as well as the commit itself.
        //As SharedPreferences instances are singletons within a process, it's safe to replace any instance of commit() with apply() if you were already ignoring the return value.
        //You don't need to worry about Android component lifecycles and their interaction with apply() writing to disk. The framework makes sure in-flight disk writes from apply() complete before switching states.

        editor.putString(Name,eetFirstName.getText().toString());
        editor.putString(Age,eetAge.getText().toString());
        editor.putString(LastName,eetLastName.getText().toString());
        editor.putString(Phone,eetPhone.getText().toString());
        editor.apply();
    }
}
