package com.example.akamenov.a01newsinformation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AKamenov on 9/15/2016.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {


    private ArrayList<NewsModel> mAdapterData;
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

            mTextView = (TextView) itemView.findViewById(R.id.news_title);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    mListener.onItemSelected(position);
                }
            });
        }
    }


    public RecycleViewAdapter(ArrayList<NewsModel> data, IRecycleViewSelectedElement listener) {
        this.mAdapterData = data;
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mAdapterData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_template, parent, false);

        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (holder != null) {
            holder.mTextView.setText(mAdapterData.get(position).getTitle());
            holder.setItemPosition(position);
        }
    }
}
