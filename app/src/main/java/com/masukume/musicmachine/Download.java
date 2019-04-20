package com.masukume.musicmachine;

import android.os.Looper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 16/3/2019.
 * UMMMMMMMMMMMMMMM
 */

public class Download extends Thread {
    DownloadHandler mHandler ;

    @Override
    public void run() {
        //super.run();
        Looper.prepare();
        mHandler = new DownloadHandler();
        Looper.loop();
        /* for (String song : PlayList.playlist) {
            download();
        } */
    }


}
