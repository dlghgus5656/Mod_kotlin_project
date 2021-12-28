package com.example.mod_kotlin_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ImageButton back = findViewById(R.id.backBtn);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),DroneInformation.class);
            startActivity(intent);
        });

    }
}