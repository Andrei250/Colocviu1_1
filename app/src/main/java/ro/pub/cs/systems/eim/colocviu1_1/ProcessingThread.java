package ro.pub.cs.systems.eim.colocviu1_1;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    private String txt;
    private String date;

    public ProcessingThread(Context context, String txt) {
        this.context = context;

        SimpleDateFormat sdf = new SimpleDateFormat("'Date\n'dd-MM-yyyy '\n\nand\n\nTime\n'HH:mm:ss z");

        // on below line we are creating a variable for
        // current date and time and calling a simple
        // date format in it.
        this.date= sdf.format(new Date());
//new Date(System.currentTimeMillis())
        this.txt = txt;
    }

    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());

        sleep();
        sendMessage();

        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.INTENT_FILTER);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA, date + " " + txt);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
