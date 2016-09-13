package info.davidjensen.entvex.oneapptorulethemall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class bePicky extends AppCompatActivity {

    Button btnCancel_bePicky;
    Button btnOK_bePicky;

    NumberPicker nbpNumber_bePicky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_picky);

        btnCancel_bePicky = (Button) findViewById(R.id.btnCancel_bePicky);
        btnOK_bePicky = (Button) findViewById(R.id.btnOK_bePicky);

        nbpNumber_bePicky = (NumberPicker) findViewById(R.id.nbpNumber_bePicky);

        nbpNumber_bePicky.setMinValue(0);
        nbpNumber_bePicky.setMaxValue(1000);

        btnOK_bePicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent data = new Intent();
                data.putExtra("BePicky", "The nummber from BePicky was " + String.valueOf(nbpNumber_bePicky.getValue()));
                setResult(RESULT_OK, data);
                finish();
            }
        });

        btnCancel_bePicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}