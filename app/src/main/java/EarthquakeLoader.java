import android.content.AsyncTaskLoader;

import com.example.android.quakereport.Earthquake;

import java.util.List;

/**
 * Created by jaspreet.singh on 1/8/2018.
 */

/** Loads a list of Earthquake by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
}
