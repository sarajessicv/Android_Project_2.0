package com.example.android_project_20;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class SalaryInfo extends AppCompatActivity {

    private ListView listview;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salary_info);
        listview = findViewById(R.id.listview);
        context = this;

            ArrayList<String> data = new ArrayList();


        try {
            BufferedReader reader = new BufferedReader(new FileReader((this.getFilesDir()) + "/Salary_list.txt"));
            String line = reader.readLine();
            while(line != null){
                data.add(line);
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listview.setAdapter(new ArrayAdapter<String>(context, R.layout.list_detail, data));


    }


}
