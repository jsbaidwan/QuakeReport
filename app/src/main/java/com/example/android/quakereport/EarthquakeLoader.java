package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by jaspreet.singh on 1/11/2018.
 */

/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    // Tag for Log messages
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    // Query URL
    private String mUrl;

    /**
     * Constructs a new {@link Earthquake}
     *
     * @param context of the activity
     * @param url to load the data from
     */
    public EarthquakeLoader (Context context, String url)   {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on the background thread
     */
    @Override
    public List<Earthquake> loadInBackground() {
        if(mUrl == null)    {
            return null;
        }

        // Perform the network request, parse the response and extract a list of earthquakes.
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
}
