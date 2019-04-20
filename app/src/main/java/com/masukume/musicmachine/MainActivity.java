package com.masukume.musicmachine;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG =MainActivity.class.getSimpleName() ;
    private PlayerService mPlayerService;
    public static final String KEY_SONG ="song" ;
    boolean mBound = false;
    Button mDownload;
    Messenger mMessenger;
    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
           PlayerService.LocalBinder localBinder = (PlayerService.LocalBinder) binder;
           /* mMessenger = new Messenger(binder);
            Message message = Message.obtain();
            message.replyTo;
            try {
                mMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            */
            mBound = true;
            mPlayerService = localBinder.getService();
            if(mPlayerService.isPlaying()) {
               mButton.setText("Pause");
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* final Download thread = new Download();
        thread.setName("DownloadThread");
        thread.start();
        */

        mButton = (Button)findViewById(R.id.buttonPlay);
        mDownload = (Button)findViewById(R.id.downloadBtn);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mBound){
                    if(mPlayerService.isPlaying()) {
                        mPlayerService.pause();
                        mButton.setText("Pause");
                    }
                    else{
                        mPlayerService.play();
                        mButton.setText("Play");
                    }
                }
            }
        });
        mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Downloading starting", Toast.LENGTH_LONG).show();
                for (String song : PlayList.playlist) {
                    Intent intent = new Intent(MainActivity.this, DownloadIntentService.class);
                    intent.putExtra(KEY_SONG, song);
                    startService(intent);
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, PlayerService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mBound) {
            unbindService(serviceConnection);
            mBound = false;
        }
    }

    /* private void download() {
        long endTime = System.currentTimeMillis() + 10*1000;
        while(endTime > System.currentTimeMillis()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       // Toast.makeText(this, "Downloading complete", Toast.LENGTH_LONG).show();
        Log.i(TAG,"donload completed");
    } */
}
