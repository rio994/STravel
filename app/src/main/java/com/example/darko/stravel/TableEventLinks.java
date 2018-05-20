package com.example.darko.stravel;


public class TableEventLinks {
    private int ID_eventLink;
    private String eventLink;



    ////constructors
    //////////////////
    //////////////////

    public TableEventLinks(){
    }

    public TableEventLinks(int ID_eventLink,String eventLink) {
        this.ID_eventLink = ID_eventLink;
        this.eventLink = eventLink;
    }

    ///getters and setters
    ////////////////////////
    ////////////////////////
    public int getID_eventLink() {
        return ID_eventLink;
    }

    public void setID_eventLink(int ID_eventLink) {
        this.ID_eventLink = ID_eventLink;
    }

    public String getEventLink() {
        return eventLink;
    }

    public void setEventLink(String eventLink) {
        this.eventLink = eventLink;
    }


}
