package info.davidjensen.entvex.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyBoundService mService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBind = (Button) findViewById(R.id.btnBind);
        Button btnUnBind = (Button) findViewById(R.id.btnUnbind);
        Button btnGetData = (Button) findViewById(R.id.btnGetData);

        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Bind to MyBoundService
                Toast.makeText(getApplicationContext(), "Binding to service", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MyBoundService.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            }
        });

        btnUnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Unbind from the service
                if (mBound) {
                    Toast.makeText(getApplicationContext(), "Unbinding from service", Toast.LENGTH_SHORT).show();
                    unbindService(mConnection);
                    mBound = false;
                }
            }
        });

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBound) {
                    // Call a method from the MyBoundService.
                    // However, if this call were something that might hang, then this request should
                    // occur in a separate thread to avoid slowing down the activity performance.
                    int num = mService.GetSate();
                    Toast.makeText(getApplicationContext(), "number: " + num, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MyBoundService.LocalBinder binder = (MyBoundService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

}
