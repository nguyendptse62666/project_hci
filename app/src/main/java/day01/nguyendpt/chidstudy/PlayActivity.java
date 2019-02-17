package day01.nguyendpt.chidstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class PlayActivity extends AppCompatActivity {

    private String topic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent intent = this.getIntent();
        topic = intent.getStringExtra("topic");
        controlTopic(topic);
    }

    public void controlTopic(String topic){
        switch (topic){
            case "animal":
                break;
            case "thing":
                break;
            case "nature":
                break;
                default:
                    return;
        }
    }
}
