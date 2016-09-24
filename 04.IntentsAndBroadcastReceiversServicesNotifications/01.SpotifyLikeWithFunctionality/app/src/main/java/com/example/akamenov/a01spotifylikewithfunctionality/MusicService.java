package com.example.akamenov.a01spotifylikewithfunctionality;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static android.provider.CalendarContract.CalendarCache.URI;

/**
 * Created by AKamenov on 9/23/2016.
 */

public class MusicService extends Service {

    MediaPlayer player;
    IBinder binder = new MusicServiceBinder();
    IServiceCommunication callback;

    public class MusicServiceBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    public void setServiceCallback(IServiceCommunication callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;

    }

    @Override
    public void onCreate() {
        player = new MediaPlayer();

        player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);

        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent.hasExtra("song")) {
            int rawId = intent.getIntExtra("song", -1);

            if (rawId != -1) {
                if (!player.isPlaying()){
                    player = MediaPlayer.create(this, rawId);
                    player.start();
                }
            }
        }

        if(intent.hasExtra("forward_song")) {
            int rawId = intent.getIntExtra("forward_song", -1);

            if (rawId != -1) {
                if (player.isPlaying()) {
                    int pos = player.getCurrentPosition();
                    pos += 10000; // milliseconds
                    player.seekTo(pos);
                }
            }
        }

        if(intent.hasExtra("reverse_song")) {
            int rawId = intent.getIntExtra("reverse_song", -1);

            if (rawId != -1) {
                if (player.isPlaying()) {
                    int pos = player.getCurrentPosition();
                    pos -= 10000; // milliseconds
                    player.seekTo(pos);
                }
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (player != null) {
            player.stop();
        }
    }
}
