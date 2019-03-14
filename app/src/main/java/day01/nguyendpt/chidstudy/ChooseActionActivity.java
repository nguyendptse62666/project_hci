package day01.nguyendpt.chidstudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import day01.nguyendpt.chidstudy.service.PlayerService;

public class ChooseActionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_action);

        TextView txtName = findViewById(R.id.txtName);
        SharedPreferences sharedPreferences = getSharedPreferences("day01.nguyendpt.chidstudy_preferences", MODE_PRIVATE);
        String playerName = sharedPreferences.getString("edtPlayerName", "Bạn nhỏ");
        txtName.setText(playerName);
    }

    public void clickToStudy(View view) {
        Intent intent = new Intent(this, ChooseTopicActivity.class);
        intent.putExtra("action","learn");
        startActivity(intent);
    }

    public void clickToPlay(View view) {
        Intent intent = new Intent(this, ChooseTopicActivity.class);
        intent.putExtra("action","play");
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
