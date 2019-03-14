package day01.nguyendpt.chidstudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import day01.nguyendpt.chidstudy.service.PlayerService;

public class EnterNameActivity extends AppCompatActivity {

    private ImageView ivEnterNameIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
        ivEnterNameIcon = findViewById(R.id.ivEnterNameIcon);
        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.buttom_animation);
        ivEnterNameIcon.setAnimation(shake);
    }

    public void clickToEnterName(View view) {
        EditText txtName= findViewById(R.id.edtName);
        String name= txtName.getText().toString();
        if(name.trim().length() <= 0){
            Toast.makeText(getApplicationContext(), "Bạn vui lòng nhập tên nhé!", Toast.LENGTH_LONG).show();
            return;
        } else {
            name = name.toLowerCase();
            name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
            SharedPreferences sharedPreferences = getSharedPreferences("day01.nguyendpt.chidstudy_preferences",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("edtPlayerName", name);
            editor.commit();
            Intent intent = new Intent(this, ChooseActionActivity.class);
            startActivity(intent);
            finish();
        }

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
