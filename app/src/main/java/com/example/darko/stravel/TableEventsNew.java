package com.example.darko.stravel;


//Class used for storing one Event

import android.graphics.Bitmap;

public class TableEventsNew {
    private String nameOfEvent;
    private String date;
    private String time;
    private String category;
    private String price;
    private String description;
    private String location;
    private Bitmap bitmap;

    public void setNameOfEvent(String nameOfEvent){
        this.nameOfEvent=nameOfEvent;
    }

    public String getNameOfEvent() {
        return nameOfEvent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location){this.location = location; }

    public String getLocation() { return location; }

    public String getPrice() {return price; }

    public void setPrice(String price) { this.price = price;}

    public void setBitmap(Bitmap bitmap){this.bitmap = bitmap; }

    public Bitmap getBitmap() {return bitmap; }
}
