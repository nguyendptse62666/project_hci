package day01.nguyendpt.chidstudy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import day01.nguyendpt.chidstudy.CustomControl.CoustomTextView;
import day01.nguyendpt.chidstudy.service.DingService;
import day01.nguyendpt.chidstudy.service.PlayerService;
import day01.nguyendpt.chidstudy.service.RightWrongPlayService;

public class PlayActivity extends AppCompatActivity {

    private String topic, recentName, action;
    private ObjectPlay objectPlay;
    private GeneratorDataClass generatorDataClass;
    private TextView txtCategory;
    private ImageView imageView;
    private Button button1, button2, button3, button4;
    private int backgroundResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent intent = this.getIntent();
        topic = intent.getStringExtra("topic");
        if(intent.hasExtra("recentName")){
            recentName = intent.getStringExtra("recentName");
        } else {
            recentName = "";
        }
        if(intent.hasExtra("action")){
            action = intent.getStringExtra("action");
        }

        generatorDataClass = new GeneratorDataClass();
        objectPlay = generatorDataClass.getObjectPlay(topic, recentName);

        txtCategory = findViewById(R.id.txtCategory);
        txtCategory.setText("Chủ đề: "+ getCategory(objectPlay.getCategory()));

        imageView = findViewById(R.id.imageQuestion);
        imageView.setImageResource(objectPlay.getImage());


        button1 = findViewById(R.id.btnAnswer1);
        button1.setText(objectPlay.getAnswers()[0]);

        button2 = findViewById(R.id.btnAnswer2);
        button2.setText(objectPlay.getAnswers()[1]);

        button3 = findViewById(R.id.btnAnswer3);
        button3.setText(objectPlay.getAnswers()[2]);

        button4 = findViewById(R.id.btnAnswer4);
        button4.setText(objectPlay.getAnswers()[3]);


        controlTopic(topic, objectPlay);

        final Animation shakeKitty = AnimationUtils.loadAnimation(this, R.anim.buttom_animation_kitty);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button1.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    button1.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200);
                                }
                            });
                            button2.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    button2.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200);
                                }
                            });
                            button3.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    button3.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200);
                                }
                            });
                            button4.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    button4.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200);
                                }
                            });
                        }
                    }, 500);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

        imageView.setAnimation(shakeKitty);

        final CoustomTextView txtCategory = findViewById(R.id.txtCategory);
        txtCategory.animate().scaleX(1.2f).scaleY(1.2f).setDuration(800).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                txtCategory.animate().scaleX(1.0f).scaleY(1.0f).setDuration(600);
            }
        });

    }

    private String getCategory(String category){
        switch (category.toUpperCase()){
            case "ANIMAL":
                return "Animal - Con vật";
            case "NATURE":
                return  "Nature - Thiên nhiên";
            case "THING":
                return "Thing - Đồ vật";
        }
        return "Tự do";
    }
    public void controlTopic(String topic, ObjectPlay objectPlay){

        if( objectPlay.getNote() != null ){
            if(objectPlay.getNote().equalsIgnoreCase("underwater")){
                backgroundResource = R.drawable.underwater_background ;
                PlayerService.player = MediaPlayer.create(this, R.raw.under_water);
                PlayerService.player.setLooping(true);
                PlayerService.player.start();
            }
            if(objectPlay.getNote().equalsIgnoreCase("darksky")){
                backgroundResource = R.drawable.dark_sky_background;
                PlayerService.player = MediaPlayer.create(this, R.raw.little_star);
                PlayerService.player.setLooping(true);
                PlayerService.player.start();
            }
            if(objectPlay.getNote().equalsIgnoreCase("school")){
                backgroundResource = R.drawable.class_background;
                PlayerService.player = MediaPlayer.create(this, R.raw.bird_sing);
                PlayerService.player.setLooping(true);
                PlayerService.player.start();
            }
            if(objectPlay.getNote().equalsIgnoreCase("traffic")){
                backgroundResource = R.drawable.road_background;
                PlayerService.player = MediaPlayer.create(this, R.raw.bird_sing);
                PlayerService.player.setLooping(true);
                PlayerService.player.start();
            }
            if(objectPlay.getNote().equalsIgnoreCase("ocean")){
                imageView.setImageResource(0);
                backgroundResource = R.drawable.nature_ocean;
                PlayerService.player = MediaPlayer.create(this, R.raw.bird_sing);
                PlayerService.player.setLooping(true);
                PlayerService.player.start();
            }
            if(objectPlay.getNote().equalsIgnoreCase("snow")){
                imageView.setImageResource(0);
                backgroundResource = R.drawable.nature_snow;
                PlayerService.player = MediaPlayer.create(this, R.raw.little_star);
                PlayerService.player.setLooping(true);
                PlayerService.player.start();
            }
            if(objectPlay.getNote().equalsIgnoreCase("tree")){
                backgroundResource = R.drawable.animal_background;
                PlayerService.player = MediaPlayer.create(this, R.raw.bird_sing);
                PlayerService.player.setLooping(true);
                PlayerService.player.start();
            }
            if(objectPlay.getNote().equalsIgnoreCase("mountain")){
                imageView.setImageResource(0);
                backgroundResource = R.drawable.nature_mountain;
                PlayerService.player = MediaPlayer.create(this, R.raw.bird_sing);
                PlayerService.player.setLooping(true);
                PlayerService.player.start();
            }
            if(objectPlay.getNote().equalsIgnoreCase("sky")){
                imageView.setImageResource(0);
                backgroundResource = R.drawable.nature_sky;
                PlayerService.player = MediaPlayer.create(this, R.raw.bird_sing);
                PlayerService.player.setLooping(true);
                PlayerService.player.start();
            }
        } else {
            switch (topic){
                case "animal":
                    backgroundResource = R.drawable.animal_background;
                    PlayerService.player = MediaPlayer.create(this, R.raw.bird_sing);
                    PlayerService.player.setLooping(true);
                    PlayerService.player.start();
                    break;
                case "thing":
                    backgroundResource = R.drawable.thing_background;
                    break;
                case "nature":
                    backgroundResource = R.drawable.blue_cloud_1;
                    PlayerService.player = MediaPlayer.create(this, R.raw.little_star);
                    PlayerService.player.setLooping(true);
                    PlayerService.player.start();
                    break;
            }
        }
        RelativeLayout linearLayout = findViewById(R.id.layoutPlay);
        linearLayout.setBackgroundResource(backgroundResource);
        Intent dingService = new Intent(getApplicationContext(), DingService.class);
        startService(dingService);

    }

    public void clickToSubmitAnswer(View view) {
        Button button = (Button) view;
        String answer = button.getText().toString();
        if(answer.equalsIgnoreCase(objectPlay.getEngName())){
            button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.correct_icon, 0);
            submitRightAnswer();
        } else {
            button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.wrong_icon, 0);
            button.setEnabled(false);
            submitWrongAnswer();
        }
    }

    public void submitRightAnswer(){
        RightWrongPlayService.rightWrongPlayer = MediaPlayer.create(this, R.raw.right_answer);
        Intent service = new Intent(getApplicationContext(), RightWrongPlayService.class);
        startService(service);
        Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
        intent.putExtra("objectPlay", objectPlay);
        recentName = objectPlay.getEngName();
        intent.putExtra("backgroundResource", backgroundResource);
        intent.putExtra("recentName", recentName);
        intent.putExtra("action", action);
        startActivity(intent);
    }


    public void submitWrongAnswer(){
        RightWrongPlayService.rightWrongPlayer = MediaPlayer.create(this, R.raw.wrong_answer);
        Intent service = new Intent(getApplicationContext(), RightWrongPlayService.class);
        startService(service);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(900);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1);
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
