package com.example.arifin.differentservices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arifin.differentservices.MyServiceBinder.MyLocalBinder;

public class MainActivity extends AppCompatActivity {

    MyServiceBinder myServiceBinder;
    boolean isBound= false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button;
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView= findViewById(R.id.textView);
                textView.setText(myServiceBinder.getCurrentTime());
            }
        });
        //this is for Intent Service
        /*Intent intent= new Intent(this,MyIntentService.class);
        startService(intent);*/
        //this is for normal service
        /*
        Intent intent= new Intent(this,MyService.class);
        startService(intent);
        */
        //this is for bind service
        Intent intent= new Intent(this,MyServiceBinder.class);
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection= new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocalBinder myLocalBinder= (MyLocalBinder) service;
            myServiceBinder=myLocalBinder.getService();
            isBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound=false;
        }
    };
}
