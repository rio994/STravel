package com.example.darko.stravel;

/**
 * Created by Darko on 13.1.2018..
 */

public class TablePlaces
{
    int ID_place;
    float review;
    String name;
    String type;
    String subtype;
    String description;
    String work_time_begin;
    String work_time_end;
    String address;
    String telephone_number;
    String email;
    String website;


    //constructors
    ////////////////
    ////////////////

    public TablePlaces() {
    }

    public TablePlaces(int ID_place, float review, String name, String type, String subtype, String description, String work_time_begin,
                  String work_time_end, String address, String telephone_number, String email, String website)
    {
        this.ID_place = ID_place;
        this.review = review;
        this.name = name;
        this.type = type;
        this.subtype = subtype;
        this.description = description;
        this.work_time_begin = work_time_begin;
        this.work_time_end = work_time_end;
        this.address = address;
        this.telephone_number = telephone_number;
        this.email = email;
        this.website = website;
    }



    //getters and setters
    ///////////////////////
    ///////////////////////

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public float getReview() {
        return review;
    }

    public void setReview(float review) {
        this.review = review;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWork_time_begin() {
        return work_time_begin;
    }

    public void setWork_time_begin(String work_time_begin) {
        this.work_time_begin = work_time_begin;
    }

    public String getWork_time_end() {
        return work_time_end;
    }

    public void setWork_time_end(String work_time_end) {
        this.work_time_end = work_time_end;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
