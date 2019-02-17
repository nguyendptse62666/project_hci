package day01.nguyendpt.chidstudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                    SharedPreferences sharedPreferences = getSharedPreferences("day01.nguyendpt.chidstudy_preferences",MODE_PRIVATE);
                    String playerName = sharedPreferences.getString("edtPlayerName","");
                    if(playerName == null || playerName.equalsIgnoreCase("")){
                        Intent intent = new Intent(getApplicationContext(), EnterNameActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), ChooseTopicActivity.class);
                        intent.putExtra("playerName", playerName);
                        startActivity(intent);
                        finish();
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
