package com.example.test250101

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TestActivity3 : AppCompatActivity() {

    lateinit var btn1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test3)
        btn1 = findViewById(R.id.test3Btn1)
        btn1.setOnClickListener{
            val intent:Intent = Intent(this@TestActivity3, TestActivity4::class.java)
            startActivity(intent)
        }

    }
}