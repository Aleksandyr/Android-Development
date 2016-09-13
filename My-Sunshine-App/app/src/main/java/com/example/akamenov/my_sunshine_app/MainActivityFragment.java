package com.example.akamenov.my_sunshine_app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayAdapter<String> mForecastAdapter;
    private ListView mListView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =inflater.inflate(R.layout.fragment_main, container, false);

        String[] forecastArray = {
            "Today - Sunny - 88/63",
            "Tomorrow - Foggy - 70/46",
            "Weds - Cloudy - 72/63",
            "Thurs - Rainy - 64/51",
            "Fri - Foggy - 70/46",
            "Set - Sunny - 76/68"
        };

        List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));

        mForecastAdapter = new ArrayAdapter<String>(
                // The current context (this fragment's parent activity)
                this.getActivity(),
                // ID of list item layout
                R.layout.list_item_forecast,
                // ID of the textview to populate
                R.id.list_item_forecast_textview,
                // Forecast data
                weekForecast);

        // Find reference to a listview
        mListView = (ListView) rootView.findViewById(R.id.listview_forecast);
        // Set adapter to the listview
        mListView.setAdapter(mForecastAdapter);

        return rootView;
    }
}
