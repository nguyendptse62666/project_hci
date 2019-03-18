package day01.nguyendpt.chidstudy.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class RightWrongPlayService extends Service {
    public static MediaPlayer rightWrongPlayer;
    public RightWrongPlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
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
        if (rightWrongPlayer != null) {
            rightWrongPlayer.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
