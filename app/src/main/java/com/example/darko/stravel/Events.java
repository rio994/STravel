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
import java.util.LinkedHashSet;
import java.util.Scanner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class Events extends AppCompatActivity {

    String weeklyUrl;
    RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    Scanner scanner;
    String temp;
    ArrayList<String> eventLinks;
    TableEvents events;


    //adding to todo list - list calendar for next 7 days for the user to choose from  --pressing a day--> all events on the chosen day
    //adding to todo list - pressing on the place inside an event goes to place activity to that place

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        weeklyUrl ="http://infozona.hr/calendar/weekly";
        final RequestQueue queue =  Volley.newRequestQueue(this);

        //adding to todo list - getEvents
        events = new TableEvents();
        eventLinks = new ArrayList<String>();

        //populate event links from website

        StringRequest linkRequest = new StringRequest(Request.Method.GET, weeklyUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                scanner = new Scanner(response);
                while(scanner.hasNextLine()==true)
                {
                    temp = scanner.nextLine();
                    if(temp.contains("<a href='") && temp.contains("' class='thickboxx'>"))
                    {
                        eventLinks.add(temp.substring(temp.indexOf("<a href='") + 9, temp.indexOf("' class='thickboxx'>")));
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(linkRequest);

        //events.add(eventLinks)


        StringRequest dataRequest = new StringRequest(Request.Method.GET, "http://infozona.hr/" + eventLinks.get(0), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                scanner = new Scanner(response);
                while(scanner.hasNextLine()==true)
                {
                    temp = scanner.nextLine();
                    int counter = 0;

                    //adding to todo list rjesi u objektima
                    int ID_event;
                    String place;
                    String name;
                    String start_date;
                    String start_time;
                    String end_time;
                    String price;
                    String category;
                    String description;
                    String link;

                    if(temp.contains("<h1") && temp.contains("</h1>")) {
                        name = temp.substring(temp.indexOf("<h1") + 26, temp.indexOf("</h1>"));
                        //eventLinks.add(temp.substring(temp.indexOf("<a href='") + 9, temp.indexOf("' class='thickboxx'>")));
                    }

                    switch(counter)
                    {
                        case 0:
                        {
                            start_date = temp.substring(temp.indexOf("Date: <b>" + 9), temp.indexOf("</b>"));
                            temp = temp.substring(temp.indexOf("</b>") + 3,temp.length()-1);
                            counter ++;
                            break;
                        }
                        case 1:
                        {
                            start_time = temp.substring(temp.indexOf("Starts at: <b>" + 14), temp.indexOf("</b>"));
                            temp = temp.substring(temp.indexOf("</b>") + 3,temp.length()-1);
                            counter ++;
                            break;
                        }
                        case 2:
                        {
                            place = temp.substring(temp.indexOf("Location: <b>" + 13), temp.indexOf("</b>"));
                            temp = temp.substring(temp.indexOf("</b>") + 3,temp.length()-1);
                            counter ++;
                            break;
                        }
                        case 3:
                        {
                            price = temp.substring(temp.indexOf("Price: <b>" + 10), temp.indexOf("</b>"));
                            temp = temp.substring(temp.indexOf("</b>") + 3,temp.length()-1);
                            counter ++;
                            break;
                        }
                        case 4:
                        {
                            category = temp.substring(temp.indexOf("Category: <b>" + 9), temp.indexOf("</b>"));
                            temp = temp.substring(temp.indexOf("</b>") + 3,temp.length()-1);
                            counter ++;
                            break;
                        }
                        /*  multi lined string
                        case 5:
                        {
                            description = temp.substring(temp.indexOf("<p>" + 3), temp.indexOf("</p>"));
                            temp = temp.substring(temp.indexOf("</b>") + 3,temp.length()-1);
                            counter ++;
                            break;
                        }*/
                    }

                    if(temp.contains("") && temp.contains(""))
                    {

                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(dataRequest);






        // data to populate the RecyclerView with
        ArrayList<TableEvents> Events = new ArrayList<>();

        //just 1 element
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



