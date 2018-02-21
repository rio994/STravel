package com.example.darko.stravel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Info extends AppCompatActivity {

    private int lastExpandedPosition = -1;
    private ExpandableListView listView;
    private InfoListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        listView = (ExpandableListView)findViewById(R.id.lvExp);
        listView.setAlpha(0.75f);

        initData();

        listAdapter = new InfoListAdapter(this,listDataHeader,listHash,Info.this);

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


    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Phone numbers");
        listDataHeader.add("Translated phrases");

        List<String> PhoneNumbers = new ArrayList<>();
        PhoneNumbers.add("General help");
        PhoneNumbers.add("Consulates");
        PhoneNumbers.add("Health services");

        List<String> TranslatedPhrases = new ArrayList<>();
        TranslatedPhrases.add("English");
        TranslatedPhrases.add("French");
        TranslatedPhrases.add("German");
        TranslatedPhrases.add("Italian");
        TranslatedPhrases.add("Spanish");
        TranslatedPhrases.add("Russian");

        listHash.put(listDataHeader.get(0),PhoneNumbers);
        listHash.put(listDataHeader.get(1),TranslatedPhrases);
    }



    public class InfoListAdapter extends BaseExpandableListAdapter {
        private Context context;
        private List<String> listDataHeader;
        private HashMap<String,List<String>> listHashMap;
        private Activity activity;

        public InfoListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listHashMap,Activity activity) {
            this.context = context;
            this.listDataHeader = listDataHeader;
            this.listHashMap = listHashMap;
            this.activity = activity;
        }

        @Override
        public int getGroupCount() {
            return listDataHeader.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return listHashMap.get(listDataHeader.get(i)).size();
        }

        @Override
        public Object getGroup(int i) {
            return listDataHeader.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return listHashMap.get(listDataHeader.get(i)).get(i1); // i = Group Item , i1 = ChildItem
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            String headerTitle = (String)getGroup(i);
            if(view == null)
            {
                LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_group,null);
            }

            TextView lblListHeader = (TextView)view.findViewById(R.id.lblListHeader);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);
            return view;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            final String childText = (String)getChild(i,i1);

            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item,null)    ;

            final TextView txtListChild = (TextView) view.findViewById(R.id.lblListItem);
            txtListChild.setText(childText);

            txtListChild.setPadding(2 * txtListChild.getPaddingLeft(), txtListChild.getPaddingTop(),txtListChild.getPaddingRight(),txtListChild.getPaddingBottom());

            /*view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    String extra = (String) txtListChild.getText();
                    Intent intent = new Intent(activity, ListPlaces.class);
                    intent.putExtra("subtype",extra);
                    activity.startActivity(intent);
                }
            });
            */

            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }
}
