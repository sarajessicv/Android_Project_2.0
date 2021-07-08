package com.example.android_project_20;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.Calendar;


public class GetInfo extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView Date;
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

    @Override
    // https://www.geeksforgeeks.org/datepickerdialog-in-android/ DATEPICKERDIALOG CODE
    // https://o7planning.org/12621/android-timepicker TIMEPICKER CODE
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_info );

        is24HView =  true;
        Date = (TextView) findViewById(R.id.workDate);
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
                start_Time.setText(hourOfDay + ":" + minute);
            }
        });

        this.timePicker2.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                end_Time.setText(hourOfDay + ":" + minute);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalender = Calendar.getInstance();
        mCalender.set(Calendar.YEAR, year);
        mCalender.set(Calendar.MONTH, month);
        mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalender.getTime());
        Date.setText(selectedDate);
    }

    public void changeActivity(View view) {
            Intent intent = new Intent(GetInfo.this, SalaryInfo.class);
            startActivity(intent);
    }
}


