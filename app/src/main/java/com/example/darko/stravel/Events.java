package com.example.darko.stravel;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Events extends AppCompatActivity {

    RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        // data to populate the RecyclerView with
        ArrayList<TableEvents> Events = new ArrayList<>();
        Events.add(new TableEvents(1,1, "Party", "00:00", "24:00","description"));


        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(Events.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        adapter = new MyRecyclerViewAdapter(this, Events);
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



