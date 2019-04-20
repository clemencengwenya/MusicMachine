package com.masukume.musicmachine;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Admin on 16/3/2019.
 * UMMMMMMMMMMMMMMM
 */

public class DownloadService extends Service {
    private static final String TAG = DownloadService.class.getSimpleName();
    DownloadHandler mHandler;

    @Override
    public void onCreate() {
       // super.onCreate();

        Download thread = new Download();
        thread.setName("DownloadThread");
        thread.start();
        while(thread.mHandler == null){

        }
        mHandler = thread.mHandler;
        mHandler.stopService(this);
    }

    private void download(String song) {
        long endTime = System.currentTimeMillis() + 10 * 1000;
        while (endTime > System.currentTimeMillis()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Toast.makeText(this, "Downloading complete", Toast.LENGTH_LONG).show();
        Log.i(TAG, song + " download completed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            Message message = Message.obtain();
            message.obj = intent.getStringExtra(MainActivity.KEY_SONG);
            message.arg1 = startId;
            mHandler.sendMessage(message);

        return Service.START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
