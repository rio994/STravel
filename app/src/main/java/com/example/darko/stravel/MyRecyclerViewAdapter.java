package com.example.darko.stravel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<TableEventsNew> mEvents;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, ArrayList<TableEventsNew> events) {
        this.mInflater = LayoutInflater.from(context);
        this.mEvents = events;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.eventsName.setText(mEvents.get(position).getNameOfEvent());

        //adding to todo list
        //holder.eventsPlace.setText(mEvents.get(position).getID_place());
        holder.eventsDate.setText("\nDate: "+mEvents.get(position).getDate());
        holder.eventTime.setText("Time: "+mEvents.get(position).getTime());
        holder.eventDescription.setText("\nDescription\n"+mEvents.get(position).getDescription());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView eventsName;
        public TextView eventsDate;
        public TextView eventTime;
        public TextView eventDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            eventsName = itemView.findViewById(R.id.event_name);
            eventsDate = itemView.findViewById(R.id.event_date);
            eventTime = itemView.findViewById(R.id.event_time);
            eventDescription = itemView.findViewById(R.id.event_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public TableEventsNew getItem(int id) {
        return mEvents.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}