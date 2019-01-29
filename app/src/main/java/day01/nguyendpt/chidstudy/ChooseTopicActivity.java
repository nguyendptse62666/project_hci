package day01.nguyendpt.chidstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ChooseTopicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_topic);
        TextView txtName= findViewById(R.id.txtName);
        Intent intent = this.getIntent();
        String name= intent.getStringExtra("name");
        txtName.setText(name);
    }

    public void clickToChooseAnimalTopic(View view) {

    }
}
