package com.example.darko.stravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class SplashScreen extends FragmentActivity {

    //adding todo list - add a jingle up to 4sec

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    finish();
                    startService(new Intent(getApplicationContext(),PopulateEventLinksService.class));
                    Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        myThread.start();
    }
}
