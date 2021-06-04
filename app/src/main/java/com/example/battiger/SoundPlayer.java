package com.example.battiger;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {
    private static SoundPool soundPool;
    private static int winSound;
    private static int failSound;

    public SoundPlayer( Context context ){
        soundPool = new SoundPool( 2, AudioManager.STREAM_MUSIC, 0 );
        winSound = soundPool.load( context, R.raw.win, 1 );
        failSound = soundPool.load( context, R.raw.fail, 1 );
    }

    public void playWinSound(){
        soundPool.play(winSound, 1.0f, 1.0f, 1, 0, 1.0f );
    }

    public void playFailSound(){
        soundPool.play(failSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
