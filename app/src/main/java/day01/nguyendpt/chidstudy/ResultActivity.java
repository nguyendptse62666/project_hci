package day01.nguyendpt.chidstudy;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private String recentName;
    private ObjectPlay objectPlay;
    private TextView txtCategory, txtVietName;
    private Button buttonSpeech;
    private ImageView imageView;
    private static TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        objectPlay = (ObjectPlay) intent.getSerializableExtra("objectPlay");
        recentName = intent.getStringExtra("recentName");

        txtCategory = findViewById(R.id.txtCategory);
        txtCategory.setText("Category: "+objectPlay.getCategory());

        imageView = findViewById(R.id.imageQuestion);
        imageView.setImageResource(objectPlay.getImage());

        buttonSpeech = findViewById(R.id.btnResult);
        buttonSpeech.setText(objectPlay.getEngName());

        txtVietName = findViewById(R.id.txtVietName);
        txtVietName.setText(objectPlay.getVietName());

        tts = new TextToSpeech(this, this);
    }

    @Override
    protected void onDestroy() {
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if(status != TextToSpeech.ERROR){
            tts.setLanguage(Locale.ENGLISH);
        }
    }

    public void clickToSpeech(View view) {
        Button button = (Button) view;
        tts.speak(button.getText().toString(), TextToSpeech.QUEUE_FLUSH, null );
    }

    public void clickToContinue(View view) {
        Intent intent= new Intent(this, PlayActivity.class);
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
}
