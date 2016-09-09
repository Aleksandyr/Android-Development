package com.example.akamenov.a01tic_tac_toe_homework;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements IRecycleViewSelectedElement {

    private TextView mTextView;
    private Context mCtx;

    private RecyclerView mRecycleView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> data;

    private String TAG = MainActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<>();

        for (int index = 0; index < 9; index++) {
            data.add(index, String.valueOf(index));
        }

        mRecycleView = (RecyclerView) findViewById(R.id.recycleView);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);

        // specify an adapter (see) also next example
        mAdapter = new RecycleViewAdapter(data, this);

        mRecycleView.setAdapter(mAdapter);

        RecycleViewCustomDecoration itemCustomDecoration = new RecycleViewCustomDecoration();

        mRecycleView.addItemDecoration(itemCustomDecoration);

        mCtx = this;
    }

    @Override
    public void onItemSelected(int position) {
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_LONG).show();
    }
}
