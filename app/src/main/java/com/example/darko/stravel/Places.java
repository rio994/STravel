package com.example.darko.stravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Intent;
import android.widget.TextView;
import android.database.Cursor;


public class Places extends AppCompatActivity {


    private int lastExpandedPosition = -1;
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        listView = (ExpandableListView)findViewById(R.id.lvExp);
        listView.setAlpha(0.75f);

        initData();

        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash,this);

        listView.setAdapter(listAdapter);

        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    listView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String next = null;// get text from child textview to proceed into next activity
                Intent intent = new Intent(Places.this, Places.class);
                intent.putExtra("nextActivity",next);
                startActivity(intent);
            }
        });
        */


    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Accomodation");
        listDataHeader.add("Food & Drink");
        listDataHeader.add("Leisure");
        listDataHeader.add("Retail");
        listDataHeader.add("Sights");

        List<String> Accomodation = new ArrayList<>();
        Accomodation.add("Apartments");
        Accomodation.add("Hostels");
        Accomodation.add("Hotels");

        List<String> Retail = new ArrayList<>();
        Retail.add("Convenience stores");
        Retail.add("Drugstores");
        Retail.add("Supermarkets");

        List<String> Leisure = new ArrayList<>();
        Leisure.add("Movies");
        Leisure.add("Libraries");
        Leisure.add("Recreation");
        Leisure.add("Sports");
        Leisure.add("Theatre");

        List<String> FoodDrink = new ArrayList<>();
        FoodDrink.add("Bars");
        FoodDrink.add("Clubs");
        FoodDrink.add("Restaurants");

        List<String> Sights = new ArrayList<>();
        Sights.add("Cultural");
        Sights.add("Historical");

        listHash.put(listDataHeader.get(0),Accomodation);
        listHash.put(listDataHeader.get(1),FoodDrink);
        listHash.put(listDataHeader.get(2),Leisure);
        listHash.put(listDataHeader.get(3),Retail);
        listHash.put(listDataHeader.get(4),Sights);
    }
}
