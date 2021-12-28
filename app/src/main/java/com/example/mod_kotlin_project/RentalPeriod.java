package com.example.mod_kotlin_project;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RentalPeriod extends AppCompatActivity {

    Calendar myCalendar = Calendar.getInstance();
    Calendar myCalendar1 = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener myDatePicker = (view, year, month, dayOfMonth) -> {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, month);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();
    };

    DatePickerDialog.OnDateSetListener myDatePicker1 = (view, year, month, dayOfMonth) -> {
        myCalendar1.set(Calendar.YEAR, year);
        myCalendar1.set(Calendar.MONTH, month);
        myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel1();
    };

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_period);

        EditText startDate = findViewById(R.id.StartDate);
        startDate.setOnClickListener(v -> new DatePickerDialog(RentalPeriod.this, myDatePicker, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        EditText endDate = findViewById(R.id.EndDate);
        endDate.setOnClickListener(v -> new DatePickerDialog(RentalPeriod.this, myDatePicker1, myCalendar1.get(Calendar.YEAR),
                myCalendar1.get(Calendar.MONTH), myCalendar1.get(Calendar.DAY_OF_MONTH)).show());

        final EditText startTime = findViewById(R.id.StartTime);
        startTime.setOnClickListener(v -> {
            Calendar mCurrentTime = Calendar.getInstance();
            int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mCurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(RentalPeriod.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, (timePicker, selectedHour, selectedMinute) -> {
                String state = "AM";

                if (selectedHour > 12) {
                    selectedHour -= 12;
                    state = "PM";
                }

                startTime.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
            }, hour, minute, false);
            mTimePicker.setTitle("대여할 시간을 고르세요");
            mTimePicker.show();
        });

        final EditText endTime = findViewById(R.id.EndTime);
        endTime.setOnClickListener(v -> {
            Calendar mCurrentTime1 = Calendar.getInstance();
            int hour = mCurrentTime1.get(Calendar.HOUR_OF_DAY);
            int minute = mCurrentTime1.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker1;
            mTimePicker1 = new TimePickerDialog(RentalPeriod.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, (timePicker1, selectedHour, selectedMinute) -> {
                String state = "AM";

                if (selectedHour > 12) {
                    selectedHour -= 12;
                    state = "PM";
                }

                endTime.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
            }, hour, minute, false);
            mTimePicker1.setTitle("반납할 시간을 고르세요");
            mTimePicker1.show();
        });

        ImageButton timeSelect = findViewById(R.id.timeSelectBtn);
        timeSelect.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),DroneList.class);
            startActivity(intent);
        });

        ImageButton back = findViewById(R.id.backBtn);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),ShopInfo.class);
            startActivity(intent);
        });
    } //onCreate

    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText startDate = findViewById(R.id.StartDate);
        startDate.setText(sdf.format(myCalendar.getTime()));

    }

    private void updateLabel1() {
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText endDate = findViewById(R.id.EndDate);
        endDate.setText(sdf1.format(myCalendar1.getTime()));

    }

}