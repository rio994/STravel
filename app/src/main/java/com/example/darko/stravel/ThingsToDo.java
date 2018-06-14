package com.example.darko.stravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ThingsToDo extends AppCompatActivity implements View.OnClickListener {
    private Button oldSplitBtn;
    private Button waterToursBtn;
    private Button culturalBtn;
    private Button natureBtn;
    private Button dayTripsBtn;
    private Button outdoorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_to_do);
        oldSplitBtn = (Button)findViewById(R.id.oldSplit);
        waterToursBtn = (Button)findViewById(R.id.waterTours);
        culturalBtn = (Button)findViewById(R.id.culturalTours);
        natureBtn = (Button)findViewById(R.id.natureParks);
        dayTripsBtn = (Button)findViewById(R.id.dayTrips);
        outdoorBtn = (Button)findViewById(R.id.outdoorActivities);

        oldSplitBtn.setOnClickListener(this);
        waterToursBtn.setOnClickListener(this);
        culturalBtn.setOnClickListener(this);
        natureBtn.setOnClickListener(this);
        dayTripsBtn.setOnClickListener(this);
        outdoorBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.oldSplit:
                startActivity(new Intent(this,ExcursionActivity.class).putExtra("subtype","oldSplit"));
                break;
            case R.id.waterTours:
                startActivity(new Intent(this,ExcursionActivity.class).putExtra("subtype","waterTours"));
                break;
            case R.id.culturalTours:
                startActivity(new Intent(this,ExcursionActivity.class).putExtra("subtype","culturalTours"));
                break;
            case R.id.natureParks:
                startActivity(new Intent(this,ExcursionActivity.class).putExtra("subtype","natureParks"));
                break;
            case R.id.dayTrips:
                startActivity(new Intent(this,ExcursionActivity.class).putExtra("subtype","dayTrips"));
                break;
            case R.id.outdoorActivities:
                startActivity(new Intent(this,ExcursionActivity.class).putExtra("subtype","outdoorActivities"));
                break;

                default:
                    break;
        }

    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
