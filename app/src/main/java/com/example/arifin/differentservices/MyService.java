package com.example.arifin.differentservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Runnable r= new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    long futureTime= System.currentTimeMillis()+5000;
                    while(futureTime>System.currentTimeMillis()) {
                        synchronized (this){
                            try {
                                wait(futureTime - System.currentTimeMillis());
                                Log.i("Myservice", "Service is doing something");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        };

        Thread thread= new Thread(r);
        thread.start();


        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
