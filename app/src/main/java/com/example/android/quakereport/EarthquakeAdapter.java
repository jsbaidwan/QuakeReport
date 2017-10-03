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

        // Find the TextView with view ID location
        TextView locationViewOffset = (TextView) listItemView.findViewById(R.id.tv_location_offset);
        // Display the location of the current earthquake in that TextView
       // locationView.setText(currentEarthquake.getLocation());
        String location = currentEarthquake.getLocation();
        String[] locationSplit = location.split("of");
        String offsetLocation = locationSplit[0];
        String primaryLocation = locationSplit[1];
        locationViewOffset.setText(offsetLocation);


        // Find the TextView with view ID location
        TextView locationViewPrimary = (TextView) listItemView.findViewById(R.id.tv_location_primary);
        // Display the location of the current earthquake in that TextView
        locationViewPrimary.setText(primaryLocation);


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
