package com.example.android.quakereport;

/**
 * Created by jaspreet.singh on 17-07-2017.
 */

public class Earthquake {

    //Magnitude of Earthquake
    private String mMagnitude;

    //Location of Earthquake
    private String mLocation;

    //Date of Earthquake
    private String mDate;

    /**
     * Constructor for Earthquake class
     */

    public Earthquake (String magnitude, String location, String date)    {

        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    /*
    * Return the magnitude of the Earthquake
     */
    public String getMagnitude()    {
        return mMagnitude;
    }

    /*
     * Returns the location of the Earthquake
     */
    public String getLocation() {
        return mLocation;
    }

    /*
     * Returns the date of the Earthquake
     */
    public String getDate() {
        return mDate;
    }

}
