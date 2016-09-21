package info.davidjensen.entvex.intentservice_doit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFoo = (Button) findViewById(R.id.BtnFoo);
        Button btnBaz = (Button) findViewById(R.id.btnBaz);

        btnFoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyIntentService.class);
                intent.setAction("FOO");
                startService(intent);
            }
        });

        btnBaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyIntentService.class);
                intent.setAction("BAZ");
                startService(intent);
            }
        });


    }
}
