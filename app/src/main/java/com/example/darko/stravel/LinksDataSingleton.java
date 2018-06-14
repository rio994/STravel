package com.example.darko.stravel;

//class used to store and provide specific links collected from first URL connection

import java.util.ArrayList;

public class LinksDataSingleton  {
   private ArrayList<TableEventLinks> tableEventLinks;

    public ArrayList<TableEventLinks> getTableEventLinks() {
        return this.tableEventLinks;
    }

    public void setTableEventLinks(ArrayList<TableEventLinks> tableEventLinks){
        this.tableEventLinks = new ArrayList<>(tableEventLinks);
    }


    private static final LinksDataSingleton dataHolder = new LinksDataSingleton();
    public static  LinksDataSingleton getInstance(){return dataHolder;}
}
