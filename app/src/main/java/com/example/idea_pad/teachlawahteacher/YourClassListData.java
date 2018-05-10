package com.example.idea_pad.teachlawahteacher;

/**
 * Created by Idea-Pad on 6/5/2018.
 */

public class YourClassListData {

    public String name;
    public String venue;
    public String contact;
    public String time;
    public String date;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public YourClassListData() {
    }


    public YourClassListData(String name, String venue, String contact, String time, String date) {
        this.name = name;
        this.venue = venue;
        this.contact = contact;
        this.time = time;
        this.date = date;

    }
    //contact, date, name, time, venue


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
