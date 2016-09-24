package com.example.akamenov.a01spotifylikewithfunctionality;

/**
 * Created by AKamenov on 9/24/2016.
 */

public class Song {
    private long mId;
    private String mPerformer;
    private String mName;
    private int mRawId;

    public Song(long id, String performer, String name, int rawId) {
        this.mId = id;
        this.mPerformer = performer;
        this.mName = name;
        this.mRawId = rawId;
    }

    public long getId() {
        return mId;
    }

    public void setId(Long id) {
        this.mId = id;
    }

    public String getPerformer() {
        return mPerformer;
    }

    public void setPerformer(String mPerformer) {
        this.mPerformer = mPerformer;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getRawId() {
        return mRawId;
    }

    public void setRawId(int mRawId) {
        this.mRawId = mRawId;
    }
}
