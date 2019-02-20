package day01.nguyendpt.chidstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {

    private String topic, recentName;
    private ObjectPlay objectPlay;
    private GeneratorDataClass generatorDataClass;
    private TextView txtCategory;
    private ImageView imageView;
    private Button button1, button2, button3, button4;
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
        generatorDataClass = new GeneratorDataClass();
        objectPlay = generatorDataClass.getObjectPlay(topic, recentName);

        txtCategory = findViewById(R.id.txtCategory);
        txtCategory.setText("Category: "+objectPlay.getCategory());

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

        controlTopic(topic);
    }

    public void controlTopic(String topic){
        LinearLayout linearLayout = findViewById(R.id.layoutPlay);
        switch (topic){
            case "animal":
                linearLayout.setBackgroundResource(R.drawable.natural);
                break;
            case "thing":
                linearLayout.setBackgroundResource(R.drawable.thing_background);
                break;
            case "nature":
                linearLayout.setBackgroundResource(R.drawable.blue_cloud_1);
                break;
        }
    }

    public void clickToSubmitAnswer(View view) {
        Button button = (Button) view;
        String answer = button.getText().toString();
        if(answer.equalsIgnoreCase(objectPlay.getEngName())){
            submitRightAnswer();
        } else {
            submitWrongAnswer();
        }
    }

    public void submitRightAnswer(){
        Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
        intent.putExtra("objectPlay", objectPlay);
        recentName = objectPlay.getEngName();
        intent.putExtra("recentName", recentName);
        startActivity(intent);
        finish();
    }

    public void submitWrongAnswer(){
        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
    }
}
