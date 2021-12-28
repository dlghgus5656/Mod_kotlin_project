package com.example.mod_kotlin_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ShopInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);

        ImageButton back = findViewById(R.id.backBtn);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });

        ImageButton shopSelect = findViewById(R.id.shopSelectBtn);
        shopSelect.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),RentalPeriod.class);
            startActivity(intent);
        });
    }
}