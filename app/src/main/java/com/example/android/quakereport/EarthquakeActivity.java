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

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

public class EarthquakeActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    private static String LOG_TAG = EarthquakeActivity.class.getName();

    /** URL for the earthquake data from USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?" +
                    "format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really comes into play if we are using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    /** Adapter for the list of earthquakes */
    private EarthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);


        // Create a new adapter that takes an empty list of  earthquake as input
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                // Find the current earthquake that is clicked on
                Earthquake currentEarthquake = mAdapter.getItem(position);

                // Convert the String URL into URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }

        });

        getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this).forceLoad();

        // Create new AsyncTask object and
        // Start the AsyncTask to fetch the earthquake data
//        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
//        task.execute(USGS_REQUEST_URL);
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(EarthquakeActivity.this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        mAdapter.clear();
        mAdapter.addAll(earthquakes);
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        mAdapter.clear();
    }

    /**
     * {@link AsyncTask} to perform the network request on the background thread and then update
     * the UI with the list of earthquake in the response.
     *
     * AsyncTask has three generic parameters: the input type, a type used for progress updates,
     * and an output type. Our task will take a String URL, and return an Earthquake. We don't
     * do progress update, so the second parameter is void.
     *
     * We'll only override two of the method of AsyncTask: donInBackground() and onPostExecute().
     * The doInBackground() method runs on a background thread, so it can run long-running code
     * (like network activity), without interfering with responsiveness of the app.
     * Then the onPostExecute() is passed the result of doInBackground() method, but run on the
     * UI thread, so it can used the produced data to update the UI
     */
//    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {
//
//        /**
//         * This method runs on a background thread and perform the network request.
//         * We should not update the UI from the background thread, so we return the list of
//         * {@link Earthquake}s as the result.
//         */
//        @Override
//        protected List<Earthquake> doInBackground(String... urls) {
//            // Don't perform the request if there is no URLs, or the first URL is null.
//            if (urls.length < 1 || urls[0] == null)  {
//                return null;
//            }
//
//            // Fetch the Url and pass it to List of earthquake.
//            List<Earthquake> result = QueryUtils.fetchEarthquakeData(urls[0]);
//            return result;
//        }
//
//        /**
//         *  This method runs on the main UI thread after the background work has been
//         *  completed. This is method receives as input, the return value from the doInBackground()
//         *  method. First we clear out the adapter, to get rid of earthquake data from a previous
//         *  query to USGS. Then we update the adapter with the new list of earthquake,
//         *  which will trigger the ListView to re-populate the list items.
//         */
//        @Override
//        protected void onPostExecute(List<Earthquake> data) {
//            // Clear the adapter of previous earthquake data
//            mAdapter.clear();
//
//            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
//            // data set. This will trigger the ListView to update.
//            if (data != null || !data.isEmpty())    {
//                mAdapter.addAll(data);
//            }
//        }
//    }
}