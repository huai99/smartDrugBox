package com.siehuai.smartdrugbox.controller.Service;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class RingtoneService {


    private static RingtoneService mInstance;
    private MediaPlayer mMediaPlayer;
    private boolean isMediaPlayerPrepare = false;

    private RingtoneService() {
    }

    public void createMediaPlayer(Context context, int ringtoneResId) {
        if (!isMediaPlayerPrepare) {
            mMediaPlayer = MediaPlayer.create(context, ringtoneResId);
            isMediaPlayerPrepare = !isMediaPlayerPrepare;
        }
        Log.d("Ringtone", "Create: " + String.valueOf(mMediaPlayer));
    }


    public void playRingtone() {
        mMediaPlayer.start();
    }


    public void stopAndReset() {
        if (mMediaPlayer != null) {
            Log.d("Ringtone", "Stop: " + String.valueOf(mMediaPlayer));
            stopMediaPlayer();
            resetMediaPlayer();
        }
    }

    private void stopMediaPlayer(){
        mMediaPlayer.stop();
    }

    private void resetMediaPlayer(){
        mMediaPlayer.reset();
        isMediaPlayerPrepare = false;
    }

    public static RingtoneService getInstance() {
        if (mInstance == null) {
            mInstance = new RingtoneService();
        }
        return mInstance;
    }


}
