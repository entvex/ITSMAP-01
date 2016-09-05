package info.davidjensen.entvex.thereandbackagain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class SecondActivity extends AppCompatActivity {


    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Button btnGoToMainActivity = (Button) findViewById(R.id.btnGoToMainActivity);

        intent = new Intent(this,MainActivity.class );

        btnGoToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Added this flag to make the app to close all Activities in the call stack which are above your main activity
                // and bring your main activity to the top of the call stack.
                intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
