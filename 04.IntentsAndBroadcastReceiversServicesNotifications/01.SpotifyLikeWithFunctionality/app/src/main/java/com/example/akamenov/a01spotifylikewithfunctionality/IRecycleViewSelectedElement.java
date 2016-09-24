package com.example.akamenov.a01spotifylikewithfunctionality;

/**
 * Created by AKamenov on 9/23/2016.
 */

public interface IRecycleViewSelectedElement {
    void onStartButtonClicked(int position);
    void onStopButtonClicked(int position);
    void onForwardButtonClicked(int position);
    void onReverseButtonClicked(int position);
}
