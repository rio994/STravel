package com.example.darko.stravel;

/**
 * Created by Darko on 16.1.2018..
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    // Table Names
    private static final String TABLE_PLACES = "Places";
    private static final String TABLE_EVENTS = "Events";
    private static final String TABLE_EVENTLINKS = "EventLinks";

    //EventLinks Table - column names
    private static final String KEY_ID_EVENTLINKS = "ID_eventLinks";
    private static final String KEY_EVENTLINK = "event_link";

    // Common column names
    private static final String KEY_ID_PLACE = "ID_place";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_NAME = "name";

    // PLACES Table - column names
    private static final String KEY_REVIEW = "review";
    private static final String KEY_TYPE = "type";
    private static final String KEY_SUBTYPE = "subtype";
    private static final String KEY_WORK_TIME_BEGIN = "work_time_begin";
    private static final String KEY_WORK_TIME_END = "work_time_end";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_TELEPHONE_NUMBER = "telephone_number";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_WEBSITE = "website";

    // EVENTS Table - column names
    private static final String KEY_ID_EVENT = "ID_event";
    private static final String KEY_START_TIME = "start_time";
    private static final String KEY_END_TIME = "end_time";


    /**
     * Private constructor to aboid object creation from outside classes.
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
     * @return the instance of DabaseAccess
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
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */


    //return all event links

    public ArrayList<TableEventLinks> getAllEventLinks(Context context){
        ArrayList<TableEventLinks> eventLinks = new ArrayList<>();
       /* Cursor c = database.rawQuery("SELECT * FROM TableEventLinks", null);
        c.moveToNext();
        if(c.moveToFirst()){
            do{
                TableEventLinks e1 = new TableEventLinks();
                e1.setID_eventLink(c.getInt(c.getColumnIndex(KEY_ID_EVENTLINKS)));
                e1.setEventLink(c.getString(c.getColumnIndex(KEY_EVENTLINK)));
                eventLinks.add(e1);
            }while(c.moveToNext());

        }*/

        Cursor c = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                Toast.makeText(context, "Table Name=> "+c.getString(0), Toast.LENGTH_LONG).show();
                c.moveToNext();
            }
        }


        return eventLinks;
    }


    public ArrayList<TablePlaces> getPlaces() {
        ArrayList<TablePlaces> places = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT * FROM TablePlaces", null);
        c.moveToFirst();

        if (c.moveToFirst()) {
            do {
                TablePlaces pl = new TablePlaces();
                pl.setID_place(c.getInt((c.getColumnIndex(KEY_ID_PLACE))));
                pl.setReview(c.getFloat(c.getColumnIndex(KEY_REVIEW)));
                pl.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                pl.setType((c.getString(c.getColumnIndex(KEY_TYPE))));
                pl.setSubtype((c.getString(c.getColumnIndex(KEY_SUBTYPE))));
                //pl.setDescription((c.getString(c.getColumnIndex(KEY_DESCRIPTION))));
                pl.setWork_time_begin((c.getString(c.getColumnIndex(KEY_WORK_TIME_BEGIN))));
                pl.setWork_time_end((c.getString(c.getColumnIndex(KEY_WORK_TIME_END))));
                pl.setAddress((c.getString(c.getColumnIndex(KEY_ADDRESS))));
                pl.setTelephone_number((c.getString(c.getColumnIndex(KEY_TELEPHONE_NUMBER))));
                pl.setEmail((c.getString(c.getColumnIndex(KEY_EMAIL))));
                pl.setWebsite((c.getString(c.getColumnIndex(KEY_WEBSITE))));

                places.add(pl);
            } while (c.moveToNext());
        }

        c.close();
        return places;
    }


    public TablePlaces getPlace(int id) {
        TablePlaces place = new TablePlaces();
        Cursor c = database.rawQuery("SELECT * FROM TablePlaces WHERE TablePlaces.ID_place = " + id + ";" , null);
        c.moveToFirst();

        if (c.moveToFirst()) {
            place.setID_place(c.getInt((c.getColumnIndex(KEY_ID_PLACE))));
            place.setReview(c.getFloat(c.getColumnIndex(KEY_REVIEW)));
            place.setName((c.getString(c.getColumnIndex(KEY_NAME))));
            place.setType((c.getString(c.getColumnIndex(KEY_TYPE))));
            place.setSubtype((c.getString(c.getColumnIndex(KEY_SUBTYPE))));
            //pl.setDescription((c.getString(c.getColumnIndex(KEY_DESCRIPTION))));
            place.setWork_time_begin((c.getString(c.getColumnIndex(KEY_WORK_TIME_BEGIN))));
            place.setWork_time_end((c.getString(c.getColumnIndex(KEY_WORK_TIME_END))));
            place.setAddress((c.getString(c.getColumnIndex(KEY_ADDRESS))));
            place.setTelephone_number((c.getString(c.getColumnIndex(KEY_TELEPHONE_NUMBER))));
            place.setEmail((c.getString(c.getColumnIndex(KEY_EMAIL))));
            place.setWebsite((c.getString(c.getColumnIndex(KEY_WEBSITE))));

        }

        c.close();
        return place;
    }

    //adding to todo list - getBySubtypePlaces, getEvents
}
