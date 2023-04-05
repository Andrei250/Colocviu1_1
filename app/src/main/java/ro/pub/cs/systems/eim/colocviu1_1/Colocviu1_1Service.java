package ro.pub.cs.systems.eim.colocviu1_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.security.Provider;

public class Colocviu1_1Service extends Service {

    ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String txt = intent.getStringExtra(Constants.INTENT_TAG);
        processingThread = new ProcessingThread(this, txt);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
