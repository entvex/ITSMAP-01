package info.davidjensen.entvex.onemenuactiontofindthem;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean overrideDefualtHandling = false;
        switch (id)
        {
            case R.id.itemSearch:
                overrideDefualtHandling = true;

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage("test me")
                        .setTitle("Search");

                final EditText input = new EditText(this);
                builder.setView(input);

                builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), input.getText().toString(),Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), "Cancel" ,Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }

        if (overrideDefualtHandling){
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}