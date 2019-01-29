package day01.nguyendpt.chidstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EnterNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
    }

    public void clickToEnterName(View view) {
        EditText txtName= findViewById(R.id.edtName);
        String name= txtName.getText().toString();
        if(name.trim().length() <= 0){
            Toast.makeText(EnterNameActivity.this, "No name", Toast.LENGTH_LONG);
        } else {
            Intent intent = new Intent(this, ChooseTopicActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
            finish();
        }

    }
}
