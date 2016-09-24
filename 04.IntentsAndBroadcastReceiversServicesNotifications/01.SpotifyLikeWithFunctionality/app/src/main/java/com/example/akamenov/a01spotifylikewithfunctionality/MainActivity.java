package com.example.akamenov.a01spotifylikewithfunctionality;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
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

public class MainActivity extends AppCompatActivity implements IRecycleViewSelectedElement, IServiceCommunication  {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Song> mData;

    private Intent mMusicServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toolbar_title);
        toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarBackgroundColor));

        setSupportActionBar(toolbar);

        mData = new ArrayList<>();

        createListOfSongs(mData);

        mRecyclerView = (RecyclerView) findViewById(R.id.music_recycle_view);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.canScrollVertically();

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecycleViewAdapter(mData, this);

        mRecyclerView.setAdapter(mAdapter);

    }

    private void createListOfSongs(ArrayList<Song> mSongs) {
        mSongs.add(new Song((long)1, "Rick Ross", "Mafia Music", R.raw.song1));
        mSongs.add(new Song((long)2, "MEEK MILL", "ENERGY", R.raw.song2));
        mSongs.add(new Song((long)3, "gnash - (ft. olivia o'brien) [music video]", "i hate u, i love u", R.raw.song3));

    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicServiceBinder serviceToOperate = (MusicService.MusicServiceBinder)service;

            serviceToOperate.getService().setServiceCallback(MainActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onStartButtonClicked(int position) {
        mMusicServiceIntent = new Intent(this, MusicService.class);
        mMusicServiceIntent.putExtra("song", mData.get(position).getRawId());

        bindService(mMusicServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE);

        startService(mMusicServiceIntent);
    }

    @Override
    public void onStopButtonClicked(int position) {
        if (mMusicServiceIntent == null) {
            mMusicServiceIntent = new Intent(this, MusicService.class);
        }

        if (mMusicServiceIntent.hasExtra("song")) {
            int rawId = mMusicServiceIntent.getIntExtra("song", -1);
            if (rawId == mData.get(position).getRawId()) {
                unbindService(serviceConnection);
                stopService(mMusicServiceIntent);
            }
        }
    }

    @Override
    public void onForwardButtonClicked(int position) {
        if (mMusicServiceIntent == null) {
            mMusicServiceIntent = new Intent(this, MusicService.class);
        }

        if (mMusicServiceIntent.hasExtra("song")) {
            int rawId = mMusicServiceIntent.getIntExtra("song", -1);
            if (rawId == mData.get(position).getRawId()) {
                mMusicServiceIntent.putExtra("forward_song", mData.get(position).getRawId());
            }
        }

        startService(mMusicServiceIntent);
    }

    @Override
    public void onReverseButtonClicked(int position) {
        if (mMusicServiceIntent == null) {
            mMusicServiceIntent = new Intent(this, MusicService.class);
        }

        if (mMusicServiceIntent.hasExtra("song")) {
            int rawId = mMusicServiceIntent.getIntExtra("song", -1);
            if (rawId == mData.get(position).getRawId()) {
                mMusicServiceIntent.putExtra("reverse_song", mData.get(position).getRawId());
            }
        }

        startService(mMusicServiceIntent);
    }

    @Override
    public void onServiceCustomInvocation() {

    }
}
