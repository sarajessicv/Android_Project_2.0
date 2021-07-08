package com.example.android_project_20;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class WorkInfo {

    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;
    Context context;

    private static DecimalFormat df = new DecimalFormat("0.00");

    public WorkInfo(Context context) {
        this.context = context;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void writeList() throws IOException {
        LocalDateTime dateTime_start = LocalDateTime.of(date, startTime);
        LocalDateTime dateTime_end = LocalDateTime.of(date, endTime);

        float minutes = ChronoUnit.MINUTES.between(dateTime_start, dateTime_end);
        //long hours = ChronoUnit.HOURS.between(dateTime_start, dateTime_end);

        System.out.println(minutes / 60 + "##########################");
        context = context.getApplicationContext();

        File newFile = new File(String.valueOf(context.getFilesDir()), "Salary_list.txt");
        if (!newFile.exists()) {
            try {
                newFile.createNewFile();
                FileWriter fileWriter = new FileWriter(newFile);
                fileWriter.write("Day       Hours worked     Salary \n");
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SharedPreferences preferences = context.getSharedPreferences("Salary", Context.MODE_PRIVATE);
        String salary = preferences.getString("Salary", "");
        float salary_int = Float.parseFloat(salary);
            FileWriter fileWriter = new FileWriter(newFile, true);
            fileWriter.write(date + "        " + df.format(minutes / 60) + "         " + df.format(salary_int*(minutes/60)) + "\n");
            fileWriter.close();
    }


}//