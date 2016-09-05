package info.davidjensen.entvex.post_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    
    Intent intentSentDataBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Button btnOK = (Button) findViewById(R.id.btnOK);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        final EditText etvText = (EditText) findViewById(R.id.editText);

        intentSentDataBack = new Intent(this, ViewActivity.class);
        intentSentDataBack.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentSentDataBack.putExtra("text", etvText.getText().toString());
                startActivity(intentSentDataBack);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
