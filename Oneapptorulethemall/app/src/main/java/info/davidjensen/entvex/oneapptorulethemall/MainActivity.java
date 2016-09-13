package info.davidjensen.entvex.oneapptorulethemall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int BE_PICKY_REQUEST = 100;
    static final int LET_IT_SLIDE  = 200;
    static final int EDIT_TEXT_IS_MIGHTIER  = 300;

    private DemoAdaptor demoAdaptor;
    private ListView demoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ArrayList<Demo> demoList = new ArrayList<Demo>();
        for(int i = 0; i < 1000; i++){
            demoList.add(new Demo("Demo #" + (i+1), "Demo #" + (i+1) + " is a great demo"));
        }

        demoList.set(0, new Demo("bePicky", "This is a demo of using Pickers", "info.davidjensen.entvex.oneapptorulethemall.bePicky", BE_PICKY_REQUEST));
        demoList.set(1, new Demo("EditText", "Shows verious EditText inputs", "info.davidjensen.entvex.oneapptorulethemall.EditTextIsMightierThanTheSword", LET_IT_SLIDE));
        demoList.set(2, new Demo("Sliders&Color", "Demonstrates sliders and color", "info.davidjensen.entvex.oneapptorulethemall.letItSlide", EDIT_TEXT_IS_MIGHTIER));
        demoAdaptor = new DemoAdaptor(this, demoList);
        demoListView = (ListView)findViewById(R.id.listView);
        demoListView.setAdapter(demoAdaptor);

        demoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent startDemoIntent = new Intent();
                startDemoIntent.putExtra("position", position);
                String action = demoList.get(position).getIntentAction();
                int demoResultCode = demoList.get(position).getResultCode();
                if(action != null && !action.equals("")){
                    startDemoIntent.setAction(action);
                    startActivityForResult(startDemoIntent, demoResultCode);
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == BE_PICKY_REQUEST)
        {
            if (resultCode == RESULT_OK )
            {
                if (data.getStringExtra("BePicky") != null) {
                    Toast.makeText(getApplicationContext(), data.getStringExtra("BePicky"), Toast.LENGTH_LONG).show();
                }
            }
        }

        if (requestCode == EDIT_TEXT_IS_MIGHTIER)
        {
            if (resultCode == RESULT_OK )
            {
                if (data.getStringExtra("editTextIsMightierThanTheSword") != null)
                {
                    Toast.makeText(getApplicationContext(),data.getStringExtra("editTextIsMightierThanTheSword") ,Toast.LENGTH_LONG).show();
                }
            }
        }

        if (requestCode == LET_IT_SLIDE)
        {
            if (resultCode == RESULT_OK )
            {
                if (data.getStringExtra("letItSlide") != null)
                {
                    Toast.makeText(getApplicationContext(),data.getStringExtra("letItSlide") ,Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}