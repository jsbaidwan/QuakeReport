package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by jaspreet.singh on 1/11/2018.
 */

/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

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
    public List<Earthquake> loadInBackground() {
        List<Earthquake> result = QueryUtils.fetchEarthquakeData(mUrl);
        return result;
    }
}
