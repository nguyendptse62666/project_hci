package day01.nguyendpt.chidstudy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
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

import java.util.Locale;

import day01.nguyendpt.chidstudy.CustomControl.CoustomTextView;
import day01.nguyendpt.chidstudy.service.DingService;
import day01.nguyendpt.chidstudy.service.PlayerService;

public class LearnActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private String recentName, action, topic;
    private ObjectPlay objectPlay;
    private TextView txtCategory, txtVietName;
    private Button buttonSpeech;
    private ImageView imageView;
    private TextToSpeech tts;
    private int backgroundResource;
    private GeneratorDataClass generatorDataClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        Intent intent = getIntent();

        ImageView imageQuestion = findViewById(R.id.imageQuestion);
        imageQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                PlayerService.player.pause();
                setVolumeControlStream(100);
                tts.speak(button.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                PlayerService.player.start();
            }
        });

        topic = intent.getStringExtra("topic");

        if(intent.hasExtra("recentName")){
            recentName = intent.getStringExtra("recentName");
        } else {
            recentName = "";
        }
        action = intent.getStringExtra("action");

        generatorDataClass = new GeneratorDataClass();
        objectPlay = generatorDataClass.getObjectPlay(topic, recentName);

        txtCategory = findViewById(R.id.txtCategory);
        txtCategory.setText("Chủ đề: " + getCategory(objectPlay.getCategory()));

        imageView = findViewById(R.id.imageQuestion);
        imageView.setImageResource(objectPlay.getImage());

        buttonSpeech = findViewById(R.id.btnResult);
        buttonSpeech.setText(objectPlay.getEngName());

        txtVietName = findViewById(R.id.txtVietName);
        txtVietName.setText(objectPlay.getVietName());

        tts = new TextToSpeech(this, this);



        final LinearLayout btnClickContinue = findViewById(R.id.btnClickContinue);
        final LinearLayout btnClickNewTopic = findViewById(R.id.btnClickNewTopic);
        final LinearLayout btnClickGoHome = findViewById(R.id.btnClickGoHome);

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.buttom_animation_speak);
        final Animation shakeKitty = AnimationUtils.loadAnimation(this, R.anim.buttom_animation_kitty);
        final Animation shakeImage = AnimationUtils.loadAnimation(this, R.anim.image_question_animation);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setVolumeControlStream(100);
                tts.speak(objectPlay.getEngName(), TextToSpeech.QUEUE_FLUSH, null);
            }
        }, 300);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnClickContinue.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    btnClickContinue.animate().scaleX(1f).scaleY(1f).setDuration(300);
                                }
                            });
                            btnClickNewTopic.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    btnClickNewTopic.animate().scaleX(1f).scaleY(1f).setDuration(300);
                                }
                            });
                            btnClickGoHome.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    btnClickGoHome.animate().scaleX(1f).scaleY(1f).setDuration(300);
                                }
                            });

                        }}, 300);

                    buttonSpeech.startAnimation(shake);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    buttonSpeech.clearAnimation();
                    try {
                        Thread.sleep(800);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        imageView.setAnimation(shakeImage);

        final CoustomTextView txtCategory = findViewById(R.id.txtCategory);
        txtCategory.animate().scaleX(1.2f).scaleY(1.2f).setDuration(800).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                txtCategory.animate().scaleX(1.0f).scaleY(1.0f).setDuration(600);
            }
        });

        final CoustomTextView txtVIetName= findViewById(R.id.txtVietName);
        txtVIetName.animate().scaleX(1.2f).scaleY(1.2f).setDuration(800).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                txtVIetName.animate().scaleX(1.0f).scaleY(1.0f).setDuration(600);
            }
        });

        controlTopic(topic, objectPlay);

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
        RelativeLayout linearLayout = findViewById(R.id.layoutLearn);
        linearLayout.setBackgroundResource(backgroundResource);
        Intent dingService = new Intent(getApplicationContext(), DingService.class);
        startService(dingService);

    }

    private String getCategory(String category) {
        switch (category.toUpperCase()) {
            case "ANIMAL":
                return "Animal - Con vật";
            case "NATURE":
                return "Nature - Thiên nhiên";
            case "THING":
                return "Thing - Đồ vật";
        }
        return "Tự do";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.ERROR) {
            tts.setLanguage(Locale.ENGLISH);
            tts.setSpeechRate(0.8f);
        }
    }

    public void clickToSpeech(View view) {
        Button button = (Button) view;
        PlayerService.player.pause();
        setVolumeControlStream(100);
        tts.speak(button.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
        try {
            Thread.sleep(800);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PlayerService.player.start();
    }

    public void clickToContinue(View view) {
        Intent intent = new Intent(this, LearnActivity.class);
        intent.putExtra("topic", objectPlay.getCategory());
        intent.putExtra("recentName", recentName);
        intent.putExtra("action", action);
        startActivity(intent);
    }

    public void clickToGoHome(View view) {
        Intent intent = new Intent(this, ChooseActionActivity.class);
        startActivity(intent);
    }

    public void clickToChooseNewTopic(View view) {
        Intent intent = new Intent(this, ChooseTopicActivity.class);
        intent.putExtra("action", action);
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
