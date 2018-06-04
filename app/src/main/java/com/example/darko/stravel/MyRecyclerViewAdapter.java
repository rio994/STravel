package com.example.darko.stravel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<TableEventsNew> mEvents;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);



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
        holder.imgView.setImageBitmap(mEvents.get(position).getBitmap());
        holder.eventsDate.setText("Date: "+mEvents.get(position).getDate());
        holder.eventTime.setText("Time: "+mEvents.get(position).getTime());
        holder.eventLocation.setText("Location: "+mEvents.get(position).getLocation());
        if(!(mEvents.get(position).getPrice().equals("-")||mEvents.get(position).getPrice().equals("0kn"))) {
            holder.eventCategory.setText("Category: " + mEvents.get(position).getCategory());
            holder.eventPrice.setText("Price: " + mEvents.get(position).getPrice());
            if(mEvents.get(position).getDescription().contains("http"))
            holder.eventDescription.setText(mEvents.get(position).getDescription().substring(mEvents.get(position).getDescription().indexOf("http")));
        }
        else {
            holder.eventDescription.setText(mEvents.get(position).getDescription());
            holder.eventCategory.setText("Category: " + mEvents.get(position).getCategory());
            holder.eventDescription.setText( mEvents.get(position).getDescription());
            holder.eventPrice.setText("");
        }

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView eventsName;
        private TextView eventsDate;
        private TextView eventTime;
        private TextView eventLocation;
        private TextView eventCategory;
        private TextView eventDescription;
        private TextView eventPrice;
        private ImageView imgView;

        public ViewHolder(View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.imageView);
            eventsName = itemView.findViewById(R.id.event_name);
            eventsDate = itemView.findViewById(R.id.event_date);
            eventTime = itemView.findViewById(R.id.event_time);
            eventLocation = itemView.findViewById(R.id.event_location);
            eventCategory = itemView.findViewById(R.id.event_category);
            eventPrice = itemView.findViewById(R.id.event_price);
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