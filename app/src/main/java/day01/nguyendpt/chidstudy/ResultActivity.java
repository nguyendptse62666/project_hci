package day01.nguyendpt.chidstudy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
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
import day01.nguyendpt.chidstudy.service.PlayerService;

public class ResultActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private String recentName;
    private ObjectPlay objectPlay;
    private TextView txtCategory, txtVietName;
    private Button buttonSpeech;
    private ImageView imageView;
    private TextToSpeech tts;
    private int backgroundResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();

        try {
            Thread.sleep(400);
        } catch (Exception e) {
            e.printStackTrace();
        }
        objectPlay = (ObjectPlay) intent.getSerializableExtra("objectPlay");
        recentName = intent.getStringExtra("recentName");
        backgroundResource = intent.getIntExtra("backgroundResource", R.drawable.blue_cloud_1);
        txtCategory = findViewById(R.id.txtCategory);
        txtCategory.setText("Chủ đề: " + getCategory(objectPlay.getCategory()));

        imageView = findViewById(R.id.imageQuestion);
        imageView.setImageResource(objectPlay.getImage());

        buttonSpeech = findViewById(R.id.btnResult);
        buttonSpeech.setText(objectPlay.getEngName());

        txtVietName = findViewById(R.id.txtVietName);
        txtVietName.setText(objectPlay.getVietName());

        tts = new TextToSpeech(this, this);

        RelativeLayout layout = findViewById(R.id.layoutResult);
        layout.setBackgroundResource(backgroundResource);

        final LinearLayout btnClickContinue = findViewById(R.id.btnClickContinue);
        final LinearLayout btnClickNewTopic = findViewById(R.id.btnClickNewTopic);
        final ImageView imgKitty = findViewById(R.id.ImgKitty);

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.buttom_animation_speak);
        final Animation shakeKitty = AnimationUtils.loadAnimation(this, R.anim.buttom_animation_kitty);

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

                        }}, 300);

                    buttonSpeech.startAnimation(shake);
                    imgKitty.startAnimation(shakeKitty);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    buttonSpeech.clearAnimation();
                    imgKitty.clearAnimation();
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

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
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PlayerService.player.start();
    }

    public void clickToContinue(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("topic", objectPlay.getCategory());
        intent.putExtra("recentName", recentName);
        startActivity(intent);
        finish();
    }

    public void clickToChooseNewTopic(View view) {
        Intent intent = new Intent(this, ChooseTopicActivity.class);
        startActivity(intent);
        finish();
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
