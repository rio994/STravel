package com.example.darko.stravel;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleViewExcursions extends RecyclerView.Adapter<RecycleViewExcursions.ViewHolder> {

    private ArrayList<TableExcursions> mExcursion;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public RecycleViewExcursions(Context context, ArrayList<TableExcursions> excursions) {
        this.mInflater = LayoutInflater.from(context);
        this.mExcursion = excursions;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycleview_excursion_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.excursionName.setText(mExcursion.get(position).getExcursionName());
        holder.excursionOverview.setText(mExcursion.get(position).getOverview());
        holder.excursionExpectation.setText(mExcursion.get(position).getExpectation());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mExcursion.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView excursionName;
        private TextView excursionOverview;
        private TextView excursionExpectation;

        public ViewHolder(View itemView) {
            super(itemView);

            excursionName = itemView.findViewById(R.id.excursion_name);
            excursionExpectation = itemView.findViewById(R.id.excursion_expectation);
            excursionOverview = itemView.findViewById(R.id.excursion_overview);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public TableExcursions getItem(int id) {
        return mExcursion.get(id);
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