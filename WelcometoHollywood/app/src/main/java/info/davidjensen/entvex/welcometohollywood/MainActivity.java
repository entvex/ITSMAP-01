package info.davidjensen.entvex.welcometohollywood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("LifeCycle", "onCreate() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("LifeCycle", "onRestart() called");
    }


    @Override
    protected void onStart() {
        super.onStart();

        Log.d("LifeCycle", "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("LifeCycle", "onResmue() called");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("LifeCycle", "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("LifeCycle", "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("LifeCycle", "onDestroy() called");
    }
}