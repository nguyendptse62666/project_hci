package day01.nguyendpt.chidstudy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import day01.nguyendpt.chidstudy.service.PlayerService;

public class ChooseTopicActivity extends AppCompatActivity {

    private ImageView ivChooseTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_topic);

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.buttom_animation);
        final Animation kitty = AnimationUtils.loadAnimation(this, R.anim.buttom_animation_kitty);

        final Button btnAnimal = findViewById(R.id.btnAnimal);
        final Button btnThins = findViewById(R.id.btnThings);
        final Button btnNature = findViewById(R.id.btnNature);
        ivChooseTopic = findViewById(R.id.ivChooseTopicIcon);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnAnimal.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    btnAnimal.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200);
                                }
                            });
                            btnThins.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    btnThins.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200);
                                }
                            });
                            btnNature.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    btnNature.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200);
                                }
                            });
                        }}, 500);

                    btnAnimal.startAnimation(shake);
                    btnThins.startAnimation(shake);
                    btnNature.startAnimation(shake);
                    ivChooseTopic.startAnimation(kitty);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    btnAnimal.clearAnimation();
                    btnThins.clearAnimation();
                    btnNature.clearAnimation();
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        TextView txtName= findViewById(R.id.txtName);
        SharedPreferences sharedPreferences = getSharedPreferences("day01.nguyendpt.chidstudy_preferences",MODE_PRIVATE);
        String playerName = sharedPreferences.getString("edtPlayerName","");
        txtName.setText(playerName);
    }

    public void clickToChooseAnimalTopic(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("topic","animal");
        startActivity(intent);
    }

    public void clickToChooseNatureTopic(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("topic","nature");
        startActivity(intent);
    }

    public void clickToChooseThingTopic(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("topic","thing");
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
