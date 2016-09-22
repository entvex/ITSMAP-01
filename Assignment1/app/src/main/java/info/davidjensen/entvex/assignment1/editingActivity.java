package info.davidjensen.entvex.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static info.davidjensen.entvex.assignment1.MagicStringsAreEvil.AndroidDev;
import static info.davidjensen.entvex.assignment1.MagicStringsAreEvil.Id;
import static info.davidjensen.entvex.assignment1.MagicStringsAreEvil.Name;

public class editingActivity extends AppCompatActivity {

    RadioGroup rg;
    RadioButton ccbYes;
    RadioButton ccbNo;
    EditText eetName_edit;
    EditText ettID_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing);

        Button btnCancel_edit = (Button) findViewById(R.id.btnCancel_edit);
        Button btnSave_edit = (Button) findViewById(R.id.btnSave_edit);

        rg           = (RadioGroup)  findViewById(R.id.radioGroup);
        ccbYes       = (RadioButton) findViewById(R.id.ccbYes);
        ccbNo       = (RadioButton) findViewById(R.id.ccbNo);
        eetName_edit = (EditText) findViewById(R.id.eetName_main);
        ettID_edit   = (EditText) findViewById(R.id.ettID_main);

        //Load data from intent
        eetName_edit.setText(getIntent().getExtras().getString(Name));
        ettID_edit.setText(getIntent().getExtras().getString(Id));

        if(getIntent().getExtras().getBoolean(AndroidDev))
        {
            rg.check(R.id.ccbYes);
        } else {
            rg.check(R.id.ccbNo);
        }

        btnCancel_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra(Name,eetName_edit.getText().toString());

                if ( ettID_edit.getText().toString().equals("") )
                {
                    data.putExtra(Id, 0);
                }
                else {
                    data.putExtra(Id, Integer.parseInt( ettID_edit.getText().toString() ));
                }

                if( ccbYes.isChecked() )
                {
                    data.putExtra(AndroidDev, true );
                }
                else
                {
                    data.putExtra(AndroidDev, false );
                }
                setResult(RESULT_OK,data);
                finish();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString(Name, eetName_edit.getText().toString());
        savedInstanceState.putString(Id, ettID_edit.getText().toString() );

        boolean developerChecked = (rg.getCheckedRadioButtonId() == R.id.ccbYes);
        savedInstanceState.putBoolean(AndroidDev, developerChecked);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        eetName_edit.setText(savedInstanceState.getString(Name));
        ettID_edit.setText(savedInstanceState.getString(Id));

        if(savedInstanceState.getBoolean(AndroidDev)){
            rg.check(R.id.ccbYes);
        } else {
            rg.check(R.id.ccbNo);
        }
    }
}