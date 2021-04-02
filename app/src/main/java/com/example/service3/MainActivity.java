package com.example.service3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity<serviceConnection> extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private boolean mserviceConnected;
    private MyService mBoundService;
    TextView text_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_time = findViewById(R.id.button_time);
        Button button_service = findViewById(R.id.button_service);

        text_time = findViewById(R.id.text_time);

        button_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick() returned: " + mserviceConnected + "time" + mBoundService.getTimestamp());
                if(mserviceConnected) {
                    text_time.setText(mBoundService.getTimestamp());
                }
            }
        });
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            mserviceConnected = true;
            MyService.MyBinder myBinder = (MyService.MyBinder)iBinder;
            mBoundService = myBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            mserviceConnected = false;
        }
    };

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();

        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Call unbindService
    }
}
