package com.example.darko.stravel;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    //column names
    private static final String KEY_ID = "id";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_NAME = "name";
    private static final String KEY_REVIEW = "review";
    private static final String KEY_NUMBER_OF_REVIEWS = "number of reviews";
    private static final String KEY_TYPE = "type";
    private static final String KEY_SUBTYPE = "subtype";
    private static final String KEY_WORK_TIME = "working time";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_TELEPHONE_NUMBER = "phone";
    private static final String KEY_WEBSITE = "web site";
    private static final String KEY_PICTURE = "picture";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LON = "lon";
    private static final String KEY_EXPECTATION ="expectation";
    private static final String KEY_INFO = "info";


    /**
     * Private constructor to avoid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DatabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getReadableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public ArrayList<TableExcursions> getTableExcursions(){
        ArrayList<TableExcursions> tableExcursions = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM excursions", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TableExcursions element=new TableExcursions();
            element.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            element.setExcursionName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            element.setOverview(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
            element.setExpectation(cursor.getString(cursor.getColumnIndex(KEY_EXPECTATION)));
            element.setInfo(cursor.getString(cursor.getColumnIndex(KEY_INFO)));
            element.setSubtype(cursor.getString(cursor.getColumnIndex(KEY_SUBTYPE)));
            tableExcursions.add(element);
            cursor.moveToNext();
        }
        cursor.close();
        return tableExcursions;

    }

    public ArrayList<TableRestaurant> getRestaurants() {
        ArrayList<TableRestaurant> tableRestaurants = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM restraunts", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TableRestaurant element=new TableRestaurant();
            element.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            element.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            element.setLat(cursor.getString(cursor.getColumnIndex(KEY_LAT)));
            element.setLon(cursor.getString(cursor.getColumnIndex(KEY_LON)));
            element.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
            element.setWorkingTime(cursor.getString(cursor.getColumnIndex(KEY_WORK_TIME)));
            element.setPhone(cursor.getString(cursor.getColumnIndex(KEY_TELEPHONE_NUMBER)));
            element.setReview(cursor.getString(cursor.getColumnIndex(KEY_REVIEW)));
            element.setNumberOfReviews(cursor.getString(cursor.getColumnIndex(KEY_NUMBER_OF_REVIEWS)));
            element.setPicture(cursor.getString(cursor.getColumnIndex(KEY_PICTURE)));
            element.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
            element.setType(cursor.getString(cursor.getColumnIndex(KEY_TYPE)));
            tableRestaurants.add(element);
            cursor.moveToNext();
        }
        cursor.close();
        return tableRestaurants;
    }

    public ArrayList<TableTransport> getTransport() {
        ArrayList<TableTransport> tableTransports = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM transport", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TableTransport element=new TableTransport();
            element.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            element.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            element.setLat(cursor.getString(cursor.getColumnIndex(KEY_LAT)));
            element.setLon(cursor.getString(cursor.getColumnIndex(KEY_LON)));
            element.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
            element.setPhone(cursor.getString(cursor.getColumnIndex(KEY_TELEPHONE_NUMBER)));
            element.setSubtype(cursor.getString(cursor.getColumnIndex(KEY_SUBTYPE)));
            element.setWorkingTime(cursor.getString(cursor.getColumnIndex(KEY_WORK_TIME)));
            element.setWebsite(cursor.getString(cursor.getColumnIndex(KEY_WEBSITE)));
            element.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
            tableTransports.add(element);
            cursor.moveToNext();
        }
        cursor.close();
        return tableTransports;
    }

    public ArrayList<TableBarShopping> getBarsAndShops() {
        ArrayList<TableBarShopping> tableBarShoppings = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM barshopping", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TableBarShopping element=new TableBarShopping();
            element.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            element.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            element.setLat(cursor.getString(cursor.getColumnIndex(KEY_LAT)));
            element.setLon(cursor.getString(cursor.getColumnIndex(KEY_LON)));
            element.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
            element.setWorkingTime(cursor.getString(cursor.getColumnIndex(KEY_WORK_TIME)));
            element.setPhone(cursor.getString(cursor.getColumnIndex(KEY_TELEPHONE_NUMBER)));
            element.setReview(cursor.getString(cursor.getColumnIndex(KEY_REVIEW)));
            element.setNumberOfReviews(cursor.getString(cursor.getColumnIndex(KEY_NUMBER_OF_REVIEWS)));
            element.setPicture(cursor.getString(cursor.getColumnIndex(KEY_PICTURE)));
            element.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
            element.setSubtype(cursor.getString(cursor.getColumnIndex(KEY_SUBTYPE)));
            tableBarShoppings.add(element);
            cursor.moveToNext();
        }
        cursor.close();
        return tableBarShoppings;
    }

    public ArrayList<TablePHP> getPHP() {
        ArrayList<TablePHP> tablePHP = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM php", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TablePHP element=new TablePHP();
            element.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            element.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            element.setLat(cursor.getString(cursor.getColumnIndex(KEY_LAT)));
            element.setLon(cursor.getString(cursor.getColumnIndex(KEY_LON)));
            element.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
            element.setPhone(cursor.getString(cursor.getColumnIndex(KEY_TELEPHONE_NUMBER)));
            element.setSubtype(cursor.getString(cursor.getColumnIndex(KEY_SUBTYPE)));
            element.setWorkingTime(cursor.getString(cursor.getColumnIndex(KEY_WORK_TIME)));
            tablePHP.add(element);
            cursor.moveToNext();
        }
        cursor.close();
        return tablePHP;
    }

    public ArrayList<TableBeach> getBeaches() {
        ArrayList<TableBeach> tableBeaches = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM beach", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TableBeach element=new TableBeach();
            element.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            element.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            element.setLat(cursor.getString(cursor.getColumnIndex(KEY_LAT)));
            element.setLon(cursor.getString(cursor.getColumnIndex(KEY_LON)));
            element.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
            element.setReview(cursor.getString(cursor.getColumnIndex(KEY_REVIEW)));
            element.setNumberOfReviews(cursor.getString(cursor.getColumnIndex(KEY_NUMBER_OF_REVIEWS)));
            element.setPicture(cursor.getString(cursor.getColumnIndex(KEY_PICTURE)));
            element.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
            tableBeaches.add(element);
            cursor.moveToNext();
        }
        cursor.close();
        return tableBeaches;
    }

    public ArrayList<TableAtmToilet> getATMsAndToilets() {
        ArrayList<TableAtmToilet> tableAtmToilet = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM atmtoilete", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TableAtmToilet element=new TableAtmToilet();
            element.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            element.setLat(cursor.getString(cursor.getColumnIndex(KEY_LAT)));
            element.setLon(cursor.getString(cursor.getColumnIndex(KEY_LON)));
            element.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
            element.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
            element.setSubtype(cursor.getString(cursor.getColumnIndex(KEY_SUBTYPE)));
            tableAtmToilet.add(element);
            cursor.moveToNext();
        }
        cursor.close();
        return tableAtmToilet;
    }


}
