package com.masukume.musicmachine;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Admin on 17/3/2019.
 * UMMMMMMMMMMMMMMM
 */

public class PlayerService extends Service {
    private static final String TAG = PlayerService.class.getSimpleName() ;
    MediaPlayer mPlayer;
    private IBinder mBinder = new LocalBinder();

 //   LocalHandler mHandler = new LocalHandler();
  //  Messenger mMessenger = new Messenger(mHandler);

    public boolean isPlaying() {

        return mPlayer.isPlaying();
    }
/*
    public class LocalHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            if(msg.arg1 == 0){}
        }
    }
    */

    public class LocalBinder extends Binder{

        public PlayerService getService(){
            return PlayerService.this;
        }
    }
    @Override
    public void onCreate() {
        //super.onCreate();
        Log.d(TAG, " onCreate");
        mPlayer = MediaPlayer.create(this, R.raw.wegweruchiichako);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, " onBind");
        return this.mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, " onUnBind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, " onDestroy");
        mPlayer.release();
    }

    public void play(){
        mPlayer.start();
    }
    public void pause(){
        mPlayer.pause();
    }
}
