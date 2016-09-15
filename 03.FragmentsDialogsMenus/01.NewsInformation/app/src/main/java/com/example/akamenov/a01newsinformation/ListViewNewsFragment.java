package com.example.akamenov.a01newsinformation;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by AKamenov on 9/15/2016.
 */
public class ListViewNewsFragment extends Fragment implements IRecycleViewSelectedElement {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<NewsModel> data;

    public interface IItemPressed {
        void onItemPressed(NewsModel newsModel);
    }

    private IItemPressed callback;

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        callback = (IItemPressed)context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view_news_layout, container, false);

        data = new ArrayList<>();
        data.add(new NewsModel("1.Car crush", "Fortunately there are no victims in this tragedy."));
        data.add(new NewsModel("2.Bomb detonation", "all people were evacuated."));
        data.add(new NewsModel("3.Fire in the forest", "The fire was extinguished"));


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);

        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mLayoutManager.canScrollVertically();

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecycleViewAdapter(data, this);

        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onItemSelected(int position) {
        if (callback != null) {
            callback.onItemPressed(this.data.get(position));
        }
    }
}
