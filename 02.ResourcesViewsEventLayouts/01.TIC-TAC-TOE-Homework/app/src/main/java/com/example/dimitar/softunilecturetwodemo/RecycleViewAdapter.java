package com.example.dimitar.softunilecturetwodemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitar on 3.9.2016 г..
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {


    private ArrayList<String> mAdapterData;
    public static IRecycleViewSelectedElement mListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        int position;


        public void setItemPosition(int position)
        {
            this.position = position;
        }




        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    mListener.onItemSelected(position);
                }
            });
        }
    }


    public RecycleViewAdapter(ArrayList<String> data, IRecycleViewSelectedElement listener) {
        this.mAdapterData = data;
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mAdapterData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_template, parent, false);

        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (holder != null) {
            holder.mTextView.setText(mAdapterData.get(position));
            holder.setItemPosition(position);
        }
    }
}
