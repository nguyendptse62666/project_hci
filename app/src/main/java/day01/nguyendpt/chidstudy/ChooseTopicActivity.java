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
import android.widget.LinearLayout;
import android.widget.TextView;

import day01.nguyendpt.chidstudy.service.PlayerService;

public class ChooseTopicActivity extends AppCompatActivity {

    private ImageView ivChooseTopic;
    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if(intent.hasExtra("action")){
            action = intent.getStringExtra("action");
        } else {
            action = "learn";
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_topic);

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.buttom_animation);
        final Animation kitty = AnimationUtils.loadAnimation(this, R.anim.buttom_animation_kitty);

        final TextView txtTitleChooseTopic = findViewById(R.id.txtTitleChooseTopic);
        final Button btnAnimal = findViewById(R.id.btnAnimal);
        final Button btnThins = findViewById(R.id.btnThings);
        final Button btnNature = findViewById(R.id.btnNature);
        ivChooseTopic = findViewById(R.id.ivChooseTopicIcon);


        txtTitleChooseTopic.animate().scaleX(1.2f).scaleY(1.2f).setDuration(800).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                txtTitleChooseTopic.animate().scaleX(1.0f).scaleY(1.0f).setDuration(600);
            }
        });

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
                        }
                    }, 500);

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

    }

    public void clickToChooseAnimalTopic(View view) {
        String topic = "animal";
        controlAction(topic);
    }

    public void clickToChooseNatureTopic(View view) {
        String topic = "nature";
        controlAction(topic);
    }

    public void clickToChooseThingTopic(View view) {
        String topic = "thing";
        controlAction(topic);
    }

    private void controlAction(String topic){
        if(action.equals("learn")){
            Intent intent = new Intent(this, LearnActivity.class);
            intent.putExtra("topic", topic);
            startActivity(intent);
        } else if(action.equals("play")){
            Intent intent = new Intent(this, PlayActivity.class);
            intent.putExtra("topic", topic);
            startActivity(intent);
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


    public void clickToGoHome(View view) {
        Intent intent = new Intent(this, ChooseActionActivity.class);
        startActivity(intent);
    }
}
