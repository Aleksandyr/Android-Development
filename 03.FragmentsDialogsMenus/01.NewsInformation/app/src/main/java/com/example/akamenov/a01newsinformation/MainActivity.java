package com.example.akamenov.a01newsinformation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListViewNewsFragment.IItemPressed {

    ListViewNewsFragment listViewNewsFragment;
    NewsInformationFragment newsInformationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewNewsFragment = (ListViewNewsFragment) getFragmentManager().findFragmentById(R.id.list_view_news_fragment);
        newsInformationFragment = (NewsInformationFragment) getFragmentManager().findFragmentById(R.id.news_details_fragment);
    }

    @Override
    public void onItemPressed(NewsModel newsModel) {
        if (listViewNewsFragment != null) {
            newsInformationFragment.onNewsDescriptionShow(newsModel.getDescription());
        }
    }
}
