package info.davidjensen.entvex.assignment1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

import static info.davidjensen.entvex.assignment1.MagicStringsAreEvil.*;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 100;
    static final int REQUEST_EDIT = 200;

    ImageView ievImage;
    TextView ttvName_main;
    TextView ttvId_main;
    CheckBox ckbAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ievImage     = (ImageView) findViewById(R.id.ievImage);
        ttvName_main = (TextView)    findViewById(R.id.ttvName_main);
        ttvId_main   = (TextView)    findViewById(R.id.ttvId_main);
        ckbAndroid   = (CheckBox)    findViewById(R.id.ckbAndroid);

        ievImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        if ( findViewById(R.id.fab) != null ) {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,editingActivity.class);
                    intent.putExtra(Name, ttvName_main.getText().toString());
                    intent.putExtra(Id, ttvId_main.getText().toString());
                    intent.putExtra(AndroidDev,ckbAndroid.isChecked());
                    startActivityForResult(intent,REQUEST_EDIT);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ievImage.setImageBitmap(imageBitmap);
        }

        if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK)
        {
            ttvName_main.setText(data.getExtras().getString(Name));
            ttvId_main.setText( String.valueOf( data.getExtras().getInt(Id)) );
            ckbAndroid.setChecked(data.getExtras().getBoolean(AndroidDev));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString(Name, ttvName_main.getText().toString());
        savedInstanceState.putString(Id, ttvId_main.getText().toString() );
        savedInstanceState.putBoolean(AndroidDev,ckbAndroid.isChecked());
        savedInstanceState.putParcelable(Image,((BitmapDrawable)ievImage.getDrawable()).getBitmap());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        ttvName_main.setText(savedInstanceState.getString(Name));
        ttvId_main.setText(savedInstanceState.getString(Id));
        ckbAndroid.setChecked(savedInstanceState.getBoolean(AndroidDev));
        ievImage.setImageBitmap((Bitmap) savedInstanceState.getParcelable(Image));
    }
}