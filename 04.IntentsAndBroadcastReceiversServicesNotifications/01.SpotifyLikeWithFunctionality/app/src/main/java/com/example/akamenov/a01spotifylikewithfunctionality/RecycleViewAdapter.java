package com.example.akamenov.a01spotifylikewithfunctionality;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AKamenov on 9/23/2016.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {


    private ArrayList<Song> mAdapterData;
    public static IRecycleViewSelectedElement mListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        Button startMusicButton;
        Button stopMusicButton;
        Button fastForwardButton;
        Button reverseButton;
        int position;

        public void setItemPosition(int position)
        {
            this.position = position;
        }

        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.text_view);
            startMusicButton = (Button) itemView.findViewById(R.id.start_music_button);
            stopMusicButton = (Button) itemView.findViewById(R.id.stop_music_button);
            fastForwardButton = (Button) itemView.findViewById(R.id.fast_forward_button);
            reverseButton = (Button) itemView.findViewById(R.id.reverse_button);

            startMusicButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onStartButtonClicked(position);
                }
            });

            stopMusicButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onStopButtonClicked(position);
                }
            });

            fastForwardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onForwardButtonClicked(position);
                }
            });

            reverseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onReverseButtonClicked(position);
                }
            });
        }
    }


    public RecycleViewAdapter(ArrayList<Song> data, IRecycleViewSelectedElement listener) {
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
            holder.mTextView.setText(mAdapterData.get(position).getPerformer() + " - " + mAdapterData.get(position).getName());
            holder.setItemPosition(position);
        }
    }
}
