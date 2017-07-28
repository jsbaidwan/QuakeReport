/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    //public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        earthquakes.add(new Earthquake("3.1", "San Francisco", "Jul 4, 2017"));
        earthquakes.add(new Earthquake ("5.3", "London","Aug 8, 2016"));
        earthquakes.add(new Earthquake ("7.6", "Tokyo", "Feb 14, 2017"));
        earthquakes.add(new Earthquake ("2.8", "Mexico City", "May 4, 2017"));
        earthquakes.add(new Earthquake ("5.5", "Moscow", "Mar 17, 2017"));
        earthquakes.add(new Earthquake ("6.9", "Rio de Janeiro", "Jul 12, 2017"));
        earthquakes.add(new Earthquake ("3.7", "Paris", "May 24, 2017"));
        earthquakes.add(new Earthquake ("1.8", "Calagry", "June 04, 2017"));

        // Create an {@link EarthquakeAdapter}, whose data source is a list of
        // {@link Earthquake}s. The adapter knows how to create list item views for each item
        // in the list.
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);



    }
}