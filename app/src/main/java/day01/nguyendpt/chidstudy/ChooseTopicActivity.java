package day01.nguyendpt.chidstudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class ChooseTopicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_topic);

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
}
