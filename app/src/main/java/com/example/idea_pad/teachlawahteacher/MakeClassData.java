package com.example.idea_pad.teachlawahteacher;

/**
 * Created by Faris1 on 30/4/2018.
 */

public class MakeClassData {

    //should all be string?
    public String name;
    public String venue;
    public String time;
    public String date;
    public String contact;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public MakeClassData() {
    }

    public MakeClassData(String name, String venue, String time, String date, String contact) {
        this.name = name;
        this.venue = venue;
        this.time = time;
        this.date = date;
        this.contact = contact;
    }
}
