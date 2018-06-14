package com.example.darko.stravel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import java.util.ArrayList;

public class ExcursionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecycleViewExcursions adapter;
    private ArrayList<TableExcursions> tableExcursions;
    private ArrayList<TableExcursions> choosedExcursion = new ArrayList<>();
    private LinearLayoutManager horizontalLayoutManagaer;
    private SnapHelper helper = new LinearSnapHelper();
    private DatabaseSingleton databaseSingleton=DatabaseSingleton.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion);
        tableExcursions = databaseSingleton.getTableExcursions();
        // which excursion was called, set adapter for subtype
        switchExcursion();
        recyclerView= (RecyclerView)findViewById(R.id.recycle_view_excursion);
        horizontalLayoutManagaer = new LinearLayoutManager(ExcursionActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView.setAdapter(adapter);
        helper.attachToRecyclerView(recyclerView);

    }

    private void switchExcursion(){
        switch (getIntent().getStringExtra("subtype")){
            case "oldSplit":
                for (int i = 0; i<tableExcursions.size();i++){
                    if(tableExcursions.get(i).getSubtype().equals("oldSplit")){
                        choosedExcursion.add(tableExcursions.get(i));
                    }
                }
                adapter = new RecycleViewExcursions(getApplicationContext(),choosedExcursion);
                break;
            case "waterTours":
                for (int i = 0; i<tableExcursions.size();i++){
                    if(tableExcursions.get(i).getSubtype().equals("waterTours")){
                        choosedExcursion.add(tableExcursions.get(i));
                    }
                }
                adapter = new RecycleViewExcursions(getApplicationContext(),choosedExcursion);
                break;
            case "culturalTours":
                for (int i = 0; i<tableExcursions.size();i++){
                    if(tableExcursions.get(i).getSubtype().equals("culturalTours")){
                        choosedExcursion.add(tableExcursions.get(i));
                    }
                }
                adapter = new RecycleViewExcursions(getApplicationContext(),choosedExcursion);
                break;
            case "natureParks":
                for (int i = 0; i<tableExcursions.size();i++){
                    if(tableExcursions.get(i).getSubtype().equals("natureParks")){
                        choosedExcursion.add(tableExcursions.get(i));
                    }
                }
                adapter = new RecycleViewExcursions(getApplicationContext(),choosedExcursion);
                break;
            case "dayTrips":
                for (int i = 0; i<tableExcursions.size();i++){
                    if(tableExcursions.get(i).getSubtype().equals("dayTrips")){
                        choosedExcursion.add(tableExcursions.get(i));
                    }
                }
                adapter = new RecycleViewExcursions(getApplicationContext(),choosedExcursion);
                break;
            case "outdoorActivities":
                for (int i = 0; i<tableExcursions.size();i++){
                    if(tableExcursions.get(i).getSubtype().equals("outdoorActivities")){
                        choosedExcursion.add(tableExcursions.get(i));
                    }
                }
                adapter = new RecycleViewExcursions(getApplicationContext(),choosedExcursion);
                break;
            default:
                break;
        }
    }
}
