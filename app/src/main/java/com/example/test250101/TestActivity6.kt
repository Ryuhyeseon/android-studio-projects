package com.example.test250101

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TestActivity6 : AppCompatActivity() {
    lateinit var tv_title: TextView
    lateinit var strTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test6)

        tv_title = findViewById(R.id.tv_title)
        strTitle = ""

        strTitle = intent.getStringExtra("title")!!
        Log.d("amrs", strTitle)
        tv_title.text = strTitle

    }
}

