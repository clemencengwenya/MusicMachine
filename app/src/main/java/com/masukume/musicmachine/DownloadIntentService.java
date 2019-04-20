package com.masukume.musicmachine;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Admin on 17/3/2019.
 * UMMMMMMMMMMMMMMM
 */

public class DownloadIntentService extends IntentService {
    private static final String TAG = DownloadIntentService.class.getSimpleName() ;


    public DownloadIntentService() {
        super("DownloadIntentservice");
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        download(intent.getStringExtra(MainActivity.KEY_SONG));
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

}
