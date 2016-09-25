package info.davidjensen.entvex.allyourdatabasearebelongtouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    MySQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        db = new MySQLiteHelper(getApplicationContext());


        // TEST DATA //
        // add Reminders
        db.addReminder(new Reminder("Dentist", "USA"));
        db.addReminder(new Reminder("Food", "Denmark"));

        // get all Reminders
        List<Reminder> list = db.getAllTasks();

        // delete one Reminder
        db.deleteTask(list.get(0));

        // get all Reminder
        db.getAllTasks();


        Button btnAddTask = (Button) findViewById(R.id.btnAddTask);
        final EditText eetTask  = (EditText) findViewById(R.id.eetTask);
        final EditText eetPlace = (EditText) findViewById(R.id.eetPlace);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addReminder(new Reminder(eetTask.getText().toString(),eetPlace.getText().toString()));
            }
        });
    }
}