package info.davidjensen.entvex.illbebackground;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class MyService extends Service {

    boolean started = false;
    static boolean stop = false;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("service onCreate", "just ran onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (started == false)
        {
            started = true;

/*            while (true) {
                try {
                    //if the UI thread is blocked for more than a few seconds (about 5 seconds currently)
                    // the user is presented with the infamous "application not responding" (ANR) dialog.
                    // So to fix the issue do work in a AsyncTask
                    Thread.sleep(20000);
                    sendLocalMessage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
            if (stop == true)
            {
                Log.d("AsyncTask ", "Stopping treads");
            }
            else
            {
                DoWork();
            }
        }
        return 1;
    }

    @Override
    public void onDestroy() {
        Log.d("service onDestroy ", "stopping threads");
        stop = true;
    }

    private void DoWork() {
        AsyncTask<String, String, String> p = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                Log.d("StatusOfstopInThread", String.valueOf(stop)  );
                try {
                    Thread.sleep(2000);
                    sendLocalMessage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {


                if (stop == true)
                {
                    Log.d("AsyncTask ", "Stopping");
                }
                else
                {
                    DoWork();
                }
                super.onPostExecute(s);
            }
        };
        p.execute();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void sendLocalMessage() {
        Log.d("service sender", "Broadcasting message");
        Intent intent = new Intent("custom-event-name");

        // You can also include some extra data.
        intent.putExtra("message", "This is some string!");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}