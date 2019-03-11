package day01.nguyendpt.chidstudy;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;

import day01.nguyendpt.chidstudy.service.PlayerService;

public class ResultActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private String recentName;
    private ObjectPlay objectPlay;
    private TextView txtCategory, txtVietName;
    private Button buttonSpeech;
    private ImageView imageView;
    private static TextToSpeech tts;
    private int backgroundResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        try {
            Thread.sleep(2000);
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
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.ERROR) {
            tts.setLanguage(Locale.ENGLISH);
            tts.setSpeechRate(0.5f);
        }
    }

    public void clickToSpeech(View view) {
        Button button = (Button) view;
        PlayerService.player.pause();
        HashMap<String, String> onlineSpeech = new HashMap<>();
        onlineSpeech.put(TextToSpeech.Engine.KEY_FEATURE_NETWORK_SYNTHESIS, "true");
        tts.speak(button.getText().toString(), TextToSpeech.QUEUE_FLUSH, onlineSpeech);
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
