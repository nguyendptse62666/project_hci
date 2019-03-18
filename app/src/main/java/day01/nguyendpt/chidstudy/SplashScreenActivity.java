package day01.nguyendpt.chidstudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import day01.nguyendpt.chidstudy.service.PlayerService;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("day01.nguyendpt.chidstudy_preferences",MODE_PRIVATE);
                String playerName = sharedPreferences.getString("edtPlayerName","");
                Intent intent = null;
                if(playerName == null || playerName.equalsIgnoreCase("")){
                    intent = new Intent(getApplicationContext(), EnterNameActivity.class);
                } else {
                    intent = new Intent(getApplicationContext(), ChooseActionActivity.class);
                }
                PlayerService.player = MediaPlayer.create(getApplicationContext(), R.raw.little_star);
                PlayerService.player.setLooping(true);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }
}
