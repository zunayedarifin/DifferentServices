package com.example.arifin.differentservices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class MyServiceBinder extends Service {

    private final IBinder binder= new MyLocalBinder();

    public MyServiceBinder() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public String getCurrentTime(){
        SimpleDateFormat dateFormat= new SimpleDateFormat("hh:mm:ss");
        return dateFormat.format(new Date());
    }

    class MyLocalBinder extends Binder{
        MyServiceBinder getService(){
            return MyServiceBinder.this;
        }
    }
}
