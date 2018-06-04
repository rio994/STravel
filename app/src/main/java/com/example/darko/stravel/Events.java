package com.example.darko.stravel;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import java.util.ArrayList;



public class Events extends AppCompatActivity  {


    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private TableEventsNewSigletonArray myData;
    private ArrayList<TableEventsNew> tableEventsNewArray;
    private SnapHelper helper;

    //adding to todo list - list calendar for next 7 days for the user to choose from  --pressing a day--> all events on the chosen day
    //adding to todo list - pressing on the place inside an event goes to place activity to that place

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        myData = TableEventsNewSigletonArray.getInstance();
        tableEventsNewArray=myData.getEventsTable();
        helper = new LinearSnapHelper();

        // set up the RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(Events.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        adapter = new MyRecyclerViewAdapter(this, tableEventsNewArray);
        recyclerView.setAdapter(adapter);
        helper.attachToRecyclerView(recyclerView);

    }

}



