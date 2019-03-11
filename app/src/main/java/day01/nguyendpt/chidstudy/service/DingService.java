package day01.nguyendpt.chidstudy.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import day01.nguyendpt.chidstudy.R;

public class DingService extends Service {
    public static MediaPlayer dingPlayer;
    public DingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dingPlayer = MediaPlayer.create(this, R.raw.ding);
        dingPlayer.setLooping(false);
        dingPlayer.setVolume(100,100);
        dingPlayer.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        dingPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dingPlayer.stop();
        dingPlayer.release();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
