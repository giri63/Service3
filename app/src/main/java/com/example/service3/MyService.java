package com.example.service3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Chronometer;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private Chronometer mChronometer;
    MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();

        mChronometer = new Chronometer(this);
        mChronometer.start();
    }

    public String getTimestamp() {
        return String.valueOf(mChronometer.getBase());
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return myBinder;
    }

    public class MyBinder extends Binder {
        MyService getService() {
            Log.d(TAG, "getService() returned: " + MyService.this);

            return MyService.this;
        }
    }
}


