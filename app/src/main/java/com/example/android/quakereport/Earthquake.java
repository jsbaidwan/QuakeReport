package com.example.android.quakereport;

/**
 * Created by jaspreet.singh on 17-07-2017.
 */

public class Earthquake {

    //Magnitude of Earthquake
    private double mMagnitude;

    //Location of Earthquake
    private String mLocation;

    //Time of Earthquake
    private long mtimeInMiliseconds;

    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param magnitude is the magnitude (size) of the earthquake
     * @param location is the city location of the earthquake
     * @param timeInMiliseconds is the time in milliseconds (from the Epoch) when the
     *  earthquake happened
     */

    public Earthquake (double magnitude, String location, long timeInMiliseconds)    {

        mMagnitude = magnitude;
        mLocation = location;
        mtimeInMiliseconds = timeInMiliseconds;
    }

    /*
    * Return the magnitude of the Earthquake
     */
    public double getMagnitude()    {
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
    public long getTimeInMiliseconds () {
        return mtimeInMiliseconds;
    }

}
