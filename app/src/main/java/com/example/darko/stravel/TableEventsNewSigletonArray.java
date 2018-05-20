package com.example.darko.stravel;

//Class used to store all events

import java.util.ArrayList;

public class TableEventsNewSigletonArray {
        private  ArrayList<TableEventsNew> eventsTable;
        //constructor
        TableEventsNewSigletonArray(){eventsTable=new ArrayList<>();}
        public ArrayList<TableEventsNew> getEventsTable() {return eventsTable;}

        public void setElementInEventsTable(TableEventsNew elementInEventsTable){
                eventsTable.add(elementInEventsTable);
        }
        private static final TableEventsNewSigletonArray dataHolder = new TableEventsNewSigletonArray();
        public static  TableEventsNewSigletonArray getInstance(){return dataHolder;}
}
