package day01.nguyendpt.chidstudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class EnterNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
    }

    public void clickToEnterName(View view) {
        EditText txtName= findViewById(R.id.edtName);
        String name= txtName.getText().toString();
        if(name.trim().length() <= 0){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập tên!", Toast.LENGTH_LONG).show();
            return;
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("day01.nguyendpt.chidstudy_preferences",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("edtPlayerName", name);
            editor.commit();
            Intent intent = new Intent(this, ChooseTopicActivity.class);
            intent.putExtra("playerName", name);
            startActivity(intent);
            finish();
        }

    }
}
