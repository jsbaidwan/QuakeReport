package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jaspreet.singh on 21-07-2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    private static final String LOCATION_SEPARATOR = " of";

    /*
     *Construct a new {@Link EarthquakeAdapter}
     *
     * @param context of the  app
     * @param earthquakes is the list of the earthquakes, which is the data source of the adapter
     */

    public EarthquakeAdapter (Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /*
     * Returns a list view that displays the information about the earthquake at the
     * given position in the list of earthquakes
     */
    @Override
    public View getView (int position, View convertView, ViewGroup parent)  {
        //Check if there is an existing list item view {called convertView} that we can reuse,
        //otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null)   {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView with view ID magnitude
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.tv_magnitude);
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(currentEarthquake.getMagnitude());

        // Get the original location string from the Earthquake object,
        // which can be formatted of "5km N of Cairo, Egypt" or "Pacific-Antarctic Ridge".
        String originalLocation = currentEarthquake.getLocation();

        // If the original location string (i.e. "5km N of Cairo, Egypt") contains
        // a primary location (Cairo, Egypt) and a location offset (5 km N of city)
        // then store the primary key location separately from the location offset in 2 strings,
        // so they can be displayed in 2 TextViews.
        String primaryLocation;
        String offsetLocation;

        // Check whether the originalLocation string contains the "of" text\\
        if(originalLocation.contains(LOCATION_SEPARATOR))   {
            // Split the  originalLocation into two different parts (as an array of String)
            // based on the "of" text. We except an array of 2 String, where
            // the first String will be "5 km of" and second will be "Cairo, Egypt"
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            // Location offset should be  "5km N " + "of"
            offsetLocation = parts[0] + LOCATION_SEPARATOR;
            // Primary location should be "Cairo, Egypt"
            primaryLocation = parts[1];
        } else {
            // Otherwise, there is no "of"  text in the originalLocation string
            // Hence, set the default location offset to say "Near By"
            offsetLocation = getContext().getString(R.string.near_the);
            // primary location will be the full location string
            primaryLocation = originalLocation;
        }

        // Find the TextView with view ID primary location
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.tv_location_primary);
        // Display the primary location of the current earthquake in that TextView
        primaryLocationView.setText(primaryLocation);

        // Find the TextView with view ID offset location
        TextView offsetLocationView = (TextView) listItemView.findViewById(R.id.tv_location_offset);
        // Display the offset location fo the current  earthquake in that TextView
        offsetLocationView.setText(offsetLocation);


        //Create a new Data object  from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMiliseconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.tv_date);
        // Format the date string (i.e. "Mar 3, 2010")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.tv_time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);


        // Return the list View item that is now showing the appropriate data
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 2007") from a Date object
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
