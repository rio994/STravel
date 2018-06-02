package com.example.darko.stravel;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;

import jp.wasabeef.blurry.Blurry;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.widget.ImageButton;
import android.os.AsyncTask;
import java.io.InputStream;
import android.util.Log;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    private ImageButton about,places,events,info,go;
    ImageView img;

    String[] permissions = {android.Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //adding to todo list - better icons, better background

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
    }

    public void openPlaces()
    {
        Intent intent = new Intent(this,Places.class);
        startActivity(intent);
    }

    public void openEvents()
    {
        Intent intent = new Intent(this,Events.class);
        startActivity(intent);
    }

    public void openInfo()
    {
        Intent intent = new Intent(this,Info.class);
        startActivity(intent);
    }

    public void openGO()
    {
        Intent intent = new Intent(this,Go.class);
        startActivity(intent);
    }


}
