package com.example.darko.stravel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Places extends AppCompatActivity {

    //adding to todo list - change list into buttons(icons)


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

        listDataHeader.add("Experience Old Split");
        listDataHeader.add("Cruises, Sailing & Water Tours");
        listDataHeader.add("Cultural & Historical Tours");
        listDataHeader.add("Nature & Parks");
        listDataHeader.add("Day Trips");
        listDataHeader.add("Outdoor Activities");


        List<String> oldSplit = new ArrayList<>();

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

        listHash.put(listDataHeader.get(1),FoodDrink);
        listHash.put(listDataHeader.get(2),Leisure);
        listHash.put(listDataHeader.get(3),Retail);
        listHash.put(listDataHeader.get(4),Sights);
    }
}
