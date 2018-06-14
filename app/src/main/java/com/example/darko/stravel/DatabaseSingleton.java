package com.example.darko.stravel;

import java.util.ArrayList;

public class DatabaseSingleton {

    private ArrayList<TableRestaurant> tableRestaurants;
    private ArrayList<TableBarShopping> tableBarShoppings;
    private ArrayList<TablePHP> tablePHPs;
    private ArrayList<TableAtmToilet> tableAtmToilets;
    private ArrayList<TableBeach> tableBeaches;
    private ArrayList<TableTransport>tableTransport;
    private ArrayList<TableExcursions>tableExcursions;


    private static final DatabaseSingleton dataHolder = new DatabaseSingleton();
    public static  DatabaseSingleton getInstance(){return dataHolder;}

    //constructor
    private DatabaseSingleton(){
        tableRestaurants =new ArrayList<>();
        tableBarShoppings=new ArrayList<>();
        tablePHPs=new ArrayList<>();
        tableAtmToilets =new ArrayList<>();
        tableBeaches=new ArrayList<>();
        tableTransport=new ArrayList<>();
        tableExcursions = new ArrayList<>();
    }

    //getters and setters
    public ArrayList<TableRestaurant> getTableRestaurants() {
        return tableRestaurants;
    }

    public void setTableRestaurants(ArrayList<TableRestaurant> tableRestaurants) {
        this.tableRestaurants = tableRestaurants;
    }

    public ArrayList<TableTransport> getTableTransport() {
        return tableTransport;
    }

    public void setTableTransport(ArrayList<TableTransport> tableTransport) {
        this.tableTransport = tableTransport;
    }

    public ArrayList<TableBarShopping> getTableBarShoppings() {
        return tableBarShoppings;
    }

    public void setTableBarShoppings(ArrayList<TableBarShopping> tableBarShoppings) {
        this.tableBarShoppings = tableBarShoppings;
    }

    public ArrayList<TablePHP> getTablePHPs() {
        return tablePHPs;
    }

    public void setTablePHPs(ArrayList<TablePHP> tablePHPs) {
        this.tablePHPs = tablePHPs;
    }

    public ArrayList<TableAtmToilet> getTableAtmToilets() {
        return tableAtmToilets;
    }

    public void setTableAtmToilets(ArrayList<TableAtmToilet> tableAtmToilets) {
        this.tableAtmToilets = tableAtmToilets;
    }

    public ArrayList<TableBeach> getTableBeaches() {
        return tableBeaches;
    }

    public void setTableBeaches(ArrayList<TableBeach> tableBeaches) {
        this.tableBeaches = tableBeaches;
    }

    public ArrayList<TableExcursions> getTableExcursions() {
        return tableExcursions;
    }

    public void setTableExcursions(ArrayList<TableExcursions> tableExcursions) {
        this.tableExcursions = tableExcursions;
    }
}
