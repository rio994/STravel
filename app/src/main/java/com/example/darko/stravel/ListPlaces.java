package com.example.darko.stravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;

public class ListPlaces extends AppCompatActivity {


    private ListView listView;
    private ArrayAdapter listAdapter;
    ArrayList<TablePlaces> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_places);


        //adding to todo list - add a filter to change display of places according to name,*,address,category, etc...

        listView = (ListView) findViewById(R.id.listView);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        //places = databaseAccess.getPlaces();
        databaseAccess.close();

        /*
        ArrayList<String> placesNames = new ArrayList<String>();
        for(int i = 0; i< places.size();i++)
        {
            placesNames.add(places.get(i).getName());
        }
        */

        listView.setAdapter(new PlacesAdapter(this,places));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TablePlaces place = (TablePlaces) listView.getAdapter().getItem(position);
                Intent intent = new Intent(ListPlaces.this,Place.class);
                intent.putExtra("id",place.getID_place());
                startActivity(intent);
            }
        });
    }


    public class PlacesAdapter extends ArrayAdapter<TablePlaces> {
        public PlacesAdapter(Context context, ArrayList<TablePlaces> places) {
            super(context, 0, places);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            TablePlaces place = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_placesitem, parent, false);
            }
            // Lookup view for data population
            TextView placeName = (TextView) convertView.findViewById(R.id.list_item_name);
            TextView placeAddress = (TextView) convertView.findViewById(R.id.list_item_address);
            TextView placeReview = (TextView) convertView.findViewById(R.id.list_item_review);
            // Populate the data into the template view using the data object
            placeName.setText(place.getName());
            placeAddress.setText(place.getAddress());
            placeReview.setText(place.getReview()+"");

            placeAddress.setPadding(Math.round((int)(1.5 * placeAddress.getPaddingLeft())),0,0,0);
            // Return the completed view to render on screen

            return convertView;
        }
    }




 }

