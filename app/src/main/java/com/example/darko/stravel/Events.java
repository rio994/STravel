package com.example.darko.stravel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;



public class Events extends AppCompatActivity {


    RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    TableEvents events;
    TableEventsNew tableEventsNew;
    TableEventsNewSigletonArray myData;
    ArrayList<TableEventsNew> tableEventsNewArray;


    //adding to todo list - list calendar for next 7 days for the user to choose from  --pressing a day--> all events on the chosen day
    //adding to todo list - pressing on the place inside an event goes to place activity to that place

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        myData = TableEventsNewSigletonArray.getInstance();
        tableEventsNewArray=myData.getEventsTable();

     /*   for(int i=0;i<tableEventsNewArray.size();i++){
            Toast.makeText(getApplicationContext(),"Name of Event: "+tableEventsNewArray.get(i).getNameOfEvent()+"\nDate: "+tableEventsNewArray.get(i).getDate()+
            "\nTime: "+tableEventsNewArray.get(i).getTime()+"\n\nDescription: "+tableEventsNewArray.get(i).getDescription(),Toast.LENGTH_LONG).show();
        }
        */




        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(Events.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        adapter = new MyRecyclerViewAdapter(this, tableEventsNewArray);
        adapter.setClickListener(new MyRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(Events.this, "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
             //   Toast.makeText(EventsActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

    }




}



