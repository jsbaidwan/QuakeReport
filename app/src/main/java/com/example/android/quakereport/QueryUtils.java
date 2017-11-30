package com.example.android.quakereport;

/**
 * Created by jaspreet.singh on 9/4/2017.
 */

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes() {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the SAMPLE_JSON_RESPONSE string
            JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONSE);

            // Extract the JSONArray associated with the key called "features",
            // which represent a list of features (or earthquake).
            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");

            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
            for(int i = 0; i < earthquakeArray.length();  i++)  {

                // Get single earthquake at position i within the list of earthquakes
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);

                // For given earthquakes, extract the JSONObject associated with the
                // key called "properties", which represents a list of all properties
                // for that earthquake.
                JSONObject properties = currentEarthquake.getJSONObject("properties");

                // Extract the value for the key called "mag"
                Double magnitude = properties.getDouble("mag");

                // Extract the value for the key called "place"
                String location = properties.getString("place");

                // Extract the value for the key called "time" in long due to Unix time
                long time = properties.getLong("time");

                //Extract the value for the key called "url"
                String url = properties.getString("url");

                // Create a new {@link Earthquake} object with the magnitude, location
                // and time from JSON response.
                Earthquake earthquake =  new Earthquake(magnitude, location, time, url);

                // Add the new {@link Earthquake} to the list of earthquakes.
                earthquakes.add(earthquake);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}