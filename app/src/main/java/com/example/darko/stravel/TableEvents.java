package com.example.darko.stravel;

/**
 * Created by Darko on 13.1.2018..
 */

public class TableEvents {
    int ID_event;
    int ID_place;
    String name;
    String start_time;
    String end_time;
    String description;


    ////constructors
    //////////////////
    //////////////////
    public TableEvents(){
    }

    public TableEvents(int ID_event, int ID_place, String name, String start_time, String end_time, String description) {
        this.ID_event = ID_event;
        this.ID_place = ID_place;
        this.name = name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.description = description;
    }

    ///getters and setters
    ////////////////////////
    ////////////////////////
    public int getID_event() {
        return ID_event;
    }

    public void setID_event(int ID_event) {
        this.ID_event = ID_event;
    }

    public int getID_place() {
        return ID_place;
    }

    public void setID_place(int ID_place) {
        this.ID_place = ID_place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
