package com.example.darko.stravel;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import android.widget.Button;

/**
 * Created by reale on 21/11/2016.
 */

public class ExpandableListWithButtonAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHashMap;

    public ExpandableListWithButtonAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
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
        view = inflater.inflate(R.layout.list_itembutton,null)    ;

        final TextView txtListChild = (TextView) view.findViewById(R.id.lblListItem);
        txtListChild.setMaxLines(5);
        txtListChild.setText(childText);

        //padding of text element
        //adding to todo list
        txtListChild.setPadding(txtListChild.getPaddingLeft()/2,txtListChild.getPaddingTop(),txtListChild.getPaddingRight()/2,txtListChild.getPaddingBottom());

        final Button butListChild = (Button) view.findViewById(R.id.MoreLessButton);

        butListChild.setOnClickListener(new View.OnClickListener(){
            final String more = "read more...";
            final String less = "read less...";

            @Override
            public void onClick(View view) {
                if(butListChild.getText() == more)
                {
                    butListChild.setText(less);
                    txtListChild.setMaxLines(TextView.MEASURED_SIZE_MASK);
                }
                else
                {
                    butListChild.setText(more);
                    txtListChild.setMaxLines(5);
                }
            }

        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}