package info.davidjensen.entvex.showmethetasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MySQLiteHelper db;
    TaskAdaptor taskAdaptor;
    ListView listView;
    List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MySQLiteHelper(this);

        //Create some dummy Database entrys
        for (int i = 1; i <= 10; i++)
        {
            db.addTask(new Task("Buy stuff","BSD aarhus"));
            db.addTask(new Task("Gym","Arnie house"));
        }

        //Load all data in the DB
        taskList = db.getAllTasks();

        //Set the Adaptor to the data.
        taskAdaptor = new TaskAdaptor(this,taskList);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(taskAdaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //Delete FROM DB
                Task task = taskList.get(position);
                db.deleteTask(task);

                //Remove from list
                taskList.remove(position);

                // Notifies the attached observers that the underlying data has been changed and any View reflecting the data set should refresh itself.
                taskAdaptor.notifyDataSetChanged();
            }
        });
    }
}