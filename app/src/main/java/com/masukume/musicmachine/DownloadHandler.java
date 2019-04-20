package com.masukume.musicmachine;

import android.os.Handler;
import android.os.Message;
import android.util.Log;



/**
 * Created by Admin on 16/3/2019.
 * UMMMMMMMMMMMMMMM
 */

public class DownloadHandler extends Handler {
    private static final String TAG =DownloadHandler.class.getSimpleName() ;
    DownloadService service;
    @Override
    public void handleMessage(Message msg) {
        // super.handleMessage(msg);
        download(msg.obj.toString());
        service.stopSelf(msg.arg1);
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

    public void stopService(DownloadService service) {
        this.service = service;
    }
}