package com.example.android_project_20;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class GetInfo extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView date;
    private Button btPickDate;
    private Button next;
    private TextView start_hint;
    private TextView end_hint;
    private TextView date_hint;

    private TimePicker timePicker;
    private TimePicker timePicker2;
    private TextView start_Time;
    private TextView end_Time;
    private Boolean is24HView;
    Context context;

    @Override
    // https://www.geeksforgeeks.org/datepickerdialog-in-android/ DATEPICKERDIALOG CODE
    // https://o7planning.org/12621/android-timepicker TIMEPICKER CODE
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_info );
        context = getApplicationContext();

        is24HView =  true;
        date = (TextView) findViewById(R.id.workDate);
        btPickDate = (Button) findViewById(R.id.btPickDate);
        next = (Button) findViewById(R.id.button_next);
        start_hint = (TextView) findViewById(R.id.start_time_hint);
        end_hint = (TextView) findViewById(R.id.end_time_hint);
        date_hint = (TextView) findViewById(R.id.date_hint);


        btPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.example.android_project_20.DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new com.example.android_project_20.DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });

        this.start_Time = (TextView) this.findViewById(R.id.start_time);
        this.timePicker = (TimePicker) this.findViewById(R.id.timePicker);
        this.end_Time= (TextView) this.findViewById(R.id.end_time2);
        this.timePicker2 = (TimePicker) this.findViewById(R.id.timePicker2);

        this.timePicker.setIs24HourView(this.is24HView);
        this.timePicker2.setIs24HourView(this.is24HView);

        this.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String minutes = String.format("%02d", minute);
                String hours = String.format("%02d", hourOfDay);
                start_Time.setText(hours + ":" + minutes);
            }
        });

        this.timePicker2.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String minutes = String.format("%02d", minute);
                String hours = String.format("%02d", hourOfDay);
                end_Time.setText(hours + ":" + minutes);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalender = Calendar.getInstance();
        mCalender.set(Calendar.YEAR, year);
        mCalender.set(Calendar.MONTH, month);
        mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance().format(mCalender.getTime());
        date.setText(selectedDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void changeActivity(View view) throws ParseException, IOException {
        WorkInfo workInfo = new WorkInfo(context);
        String dateString = date.getText().toString();
        //System.out.println("#############################"+ dateString);
        if (date.getText().toString().equals("")){
            date_hint.setText("Anna päivä");
        }
        else{
            if(start_Time.getText().toString().equals("")){
                start_hint.setText("Anna aloitusaika");
                date_hint.setText("");
            }
            else{
                if(end_Time.getText().toString().equals("")){
                    end_hint.setText("Anna lopetusaika");
                    start_hint.setText("");
                }
                else{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
                    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
                    LocalDate d = LocalDate.parse(dateString, formatter);
                    LocalTime start = LocalTime.parse(start_Time.getText().toString(),formatter1);
                    LocalTime end = LocalTime.parse(end_Time.getText().toString(),formatter1);

                    workInfo.setDate(d);
                    workInfo.setStartTime(start);
                    workInfo.setEndTime(end);
                    //System.out.println(d);
                    //System.out.println(start);
                    //System.out.println(end);
                    workInfo.writeList();


                    Intent intent = new Intent(GetInfo.this, SalaryInfo.class);
                    startActivity(intent);
                }
            }
        }

    }
}


