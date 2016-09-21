package info.davidjensen.entvex.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBoundService extends Service {

    private final IBinder iBinder = new LocalBinder();

    public MyBoundService() {
    }

    public class LocalBinder extends Binder {
        MyBoundService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MyBoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        //Return the communication channel to the service.
        Log.d("MyBoundService ", " Binding");
        return iBinder;
    }

    public int GetSate()
    {
        Log.d("MyBoundService ", "Returning data");
        return 42;
    }
}