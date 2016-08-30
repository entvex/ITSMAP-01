package info.davidjensen.entvex.clickcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    volatile int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnCounter = (Button) findViewById(R.id.btnCounter);
        final TextView tvCount = (TextView) findViewById(R.id.textView);

        //If there was a prev state restore it.
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
            tvCount.setText(String.valueOf(count));

        }


        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvCount.setText(String.valueOf(++count));
            }
        });
    }

    //Saves state
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current state
        savedInstanceState.putInt("count",count);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

}
