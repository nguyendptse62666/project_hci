package day01.nguyendpt.chidstudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import day01.nguyendpt.chidstudy.service.PlayerService;

public class ChooseActionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_action);

        PlayerService.player = MediaPlayer.create(this, R.raw.background);
        PlayerService.player.setLooping(true);
        PlayerService.player.start();

        TextView txtName = findViewById(R.id.txtName);
        SharedPreferences sharedPreferences = getSharedPreferences("day01.nguyendpt.chidstudy_preferences", MODE_PRIVATE);
        String playerName = sharedPreferences.getString("edtPlayerName", "Bạn nhỏ");
        txtName.setText(playerName);

        final LinearLayout btnStudy = findViewById(R.id.btnStudy);
        final LinearLayout btnPlay = findViewById(R.id.btnPlay);


        new Thread(new Runnable() {
            @Override
            public void run() {
                Animation shakePlay = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fly_animation);
                btnPlay.setAnimation(shakePlay);
                while (true) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            double distance = 100; //the distance to move in pixels
                            long duration = 2000; //the duration of the animation in ms

                            double direction = (Math.random() * 2 * Math.PI);

                            float translationX = (float) (Math.cos(direction) * distance);
                            float translationY = (float) (Math.sin(direction) * distance);
                            btnPlay.animate().translationX(translationX).translationY(translationY).setDuration(duration).start();
                        }
                    }, 1);
                    try {
                        Thread.sleep(1500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        try {
            Thread.sleep(650);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Animation shakeStudy = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fly_animation);
                btnStudy.setAnimation(shakeStudy);
                while (true) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            double distance = 100; //the distance to move in pixels
                            long duration = 2000; //the duration of the animation in ms

                            double direction = (Math.random() * 2 * Math.PI);

                            float translationX = (float) (Math.cos(direction) * distance);
                            float translationY = (float) (Math.sin(direction) * distance);
                            btnStudy.animate().translationX(translationX).translationY(translationY).setDuration(duration).start();
                        }
                    }, 1);
                    try {
                        Thread.sleep(1500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();





    }

    public void clickToStudy(View view) {
        Intent intent = new Intent(this, ChooseTopicActivity.class);
        intent.putExtra("action", "learn");
        startActivity(intent);
    }

    public void clickToPlay(View view) {
        Intent intent = new Intent(this, ChooseTopicActivity.class);
        intent.putExtra("action", "play");
        startActivity(intent);
    }

    public void clickToChangeName(View view) {
        Intent intent = new Intent(this, EnterNameActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        PlayerService.player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PlayerService.player.start();
    }
}
