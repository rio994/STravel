package com.example.darko.stravel;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HomeScreen extends AppCompatActivity  {

    ProgressDialog mProgressDialog;
    LinksDataSingleton linksDataSingleton;
    TableEventsNewSigletonArray tableEvents;
    private ImageButton about,places,events,info,go;
    ImageView img;

    String[] permissions = {android.Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //adding to todo list - better icons, better background

        tableEvents = TableEventsNewSigletonArray.getInstance();
        linksDataSingleton = LinksDataSingleton.getInstance();
        mProgressDialog = new ProgressDialog(this);
        about = (ImageButton) findViewById(R.id.about);
        places = (ImageButton) findViewById(R.id.places);
        events = (ImageButton) findViewById(R.id.events);
        info = (ImageButton) findViewById(R.id.info);
        go = (ImageButton) findViewById(R.id.go);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbout();
            }
        });

        places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlaces();
            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvents();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfo();
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGO();
            }
        });

    }

    public void openAbout()
    {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void openPlaces()
    {
        Intent intent = new Intent(this,ThingsToDo.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void openEvents()
    {
        if(tableEvents.getEventsTable() != null) {
            mProgressDialog.setMessage("Updating Events.....");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
            mProgressDialog.show();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    while (tableEvents.getEventsTable().size() < 3) {
                    }
                    mProgressDialog.dismiss();

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getApplicationContext(), Events.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        }
                    });
                }
            };
            thread.start();
        }

    }

    public void openInfo()
    {
        Intent intent = new Intent(this,Info.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void openGO()
    {
        Intent intent = new Intent(this,Go.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }




}
