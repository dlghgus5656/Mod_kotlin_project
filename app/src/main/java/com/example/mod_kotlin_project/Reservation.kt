package com.example.mod_kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class Reservation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val back = findViewById<ImageButton>(R.id.ic_back)
        back.setOnClickListener { v: View? ->
            val intent = Intent(applicationContext, BarActivity::class.java)
            startActivity(intent)
        }
    }
}