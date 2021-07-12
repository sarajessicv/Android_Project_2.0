package com.example.android_project_20;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    EditText salarytext;
    TextView hint;
    String salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salarytext =  findViewById(R.id.salarytext);
        hint =  findViewById(R.id.hintText);

    }

    public void changeActivity(View view) {
        salary = salarytext.getText().toString();
        if (salary.equals("")){
            hint.setText(getResources().getString(R.string.vihje_palkka));
        }
        else {
            SharedPreferences.Editor editor = this.getSharedPreferences("Salary", MODE_PRIVATE).edit();
            editor.putString("Salary", salary);
            editor.apply();

            Intent intent = new Intent(MainActivity.this, GetInfo.class);
            startActivity(intent);
        }
    }

}
