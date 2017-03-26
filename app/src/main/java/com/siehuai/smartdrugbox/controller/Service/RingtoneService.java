package com.siehuai.smartdrugbox.controller.Service;

import android.content.Context;
import android.media.MediaPlayer;

public class RingtoneService {

    private MediaPlayer mMediaPlayer;

    private static RingtoneService mInstance;

    private RingtoneService() {}

    public void createMediaPlayer(Context context, int ringtoneResId) {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(context, ringtoneResId);
        }
    }


    public void playRingtone() {
        mMediaPlayer.start();
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    public void stopAndReset() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
        }
    }

    public static RingtoneService getInstance() {
        if (mInstance == null) {
            mInstance = new RingtoneService();
        }
        return mInstance;
    }


}
