package day01.nguyendpt.chidstudy.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;

import day01.nguyendpt.chidstudy.R;

public class PlayerService extends Service {
    public static MediaPlayer player;
    public static MediaPlayer rightWrongPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.background);
        player.setLooping(true);
        player.setVolume(0.8f,0.8f);
        player.start();
        if (rightWrongPlayer != null) {
            rightWrongPlayer.setVolume(100, 100);
            rightWrongPlayer.start();
        }

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        if (rightWrongPlayer != null) {
            rightWrongPlayer.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
        if (rightWrongPlayer != null) {
            rightWrongPlayer.stop();
            rightWrongPlayer.release();
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


}
