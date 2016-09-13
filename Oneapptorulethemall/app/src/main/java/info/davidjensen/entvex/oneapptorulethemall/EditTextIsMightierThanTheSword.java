package info.davidjensen.entvex.oneapptorulethemall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditTextIsMightierThanTheSword extends AppCompatActivity {

    Button btnOK_sword;
    Button btnCancel_sword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_is_mightier_than_the_sword);

        btnOK_sword = (Button) findViewById(R.id.btnOK_sword);
        btnCancel_sword = (Button) findViewById(R.id.btnCancel_sword);

        final EditText edtPassword = (EditText) findViewById(R.id.edtPassword);
        final EditText edtEmail    = (EditText) findViewById(R.id.edtEmail);
        final EditText edtNumber   = (EditText) findViewById(R.id.edtNumber);


        btnOK_sword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra("editTextIsMightierThanTheSword", "Email is " + edtEmail.getText().toString()
                        + " Number is " + edtNumber.getText().toString()  + " The Password is "  + edtPassword.getText().toString() );
                setResult(RESULT_OK,data);
                finish();
            }
        });

        btnCancel_sword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}