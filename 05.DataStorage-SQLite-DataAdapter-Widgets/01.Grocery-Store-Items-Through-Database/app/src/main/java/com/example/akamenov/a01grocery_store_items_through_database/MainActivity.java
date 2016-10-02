package com.example.akamenov.a01grocery_store_items_through_database;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseManager mDbManager;

    private Context mCtx;

    private ArrayList<String> dataVisualisation;
    private ArrayList<FoodStuff> itemsFromDb;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button mLoadItemsButton;
    private Button mShowItemsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.mCtx = this;
        this.mDbManager = new DatabaseManager(this.mCtx);

        this.mDbManager.createTable();

        this.mLoadItemsButton = (Button) findViewById(R.id.load_items_button);
        this.mShowItemsButton = (Button) findViewById(R.id.show_items_button);

        this.mLoadItemsButton.setOnClickListener(this);
        this.mShowItemsButton.setOnClickListener(this);

        this.dataVisualisation = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            FoodStuff foodStuff = new FoodStuff(i, "food " + i);
            this.mDbManager.addFood(foodStuff);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.load_items_button) {
            itemsFromDb = this.mDbManager.getFoodStuffLimit(30);
        }

        if (v.getId() == R.id.show_items_button) {
            for (int i = 0; i < itemsFromDb.size(); i++) {
                this.dataVisualisation.add(itemsFromDb.get(i).get_name());
            }

            bindRecycleView(dataVisualisation);
        }
    }

    private void bindRecycleView(ArrayList<String> dataVisualisation) {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);

        this.mLayoutManager = new LinearLayoutManager(this);
        this.mLayoutManager.canScrollVertically();

        this.mRecyclerView.setLayoutManager(mLayoutManager);

        this.mAdapter = new RecycleViewAdapter(dataVisualisation);

        this.mRecyclerView.setAdapter(mAdapter);
    }
}
