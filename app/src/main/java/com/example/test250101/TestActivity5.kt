package com.example.test250101

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TestActivity5 : AppCompatActivity() {
    lateinit var title:String

    lateinit var test1Btn: Button
    lateinit var test2Btn: Button
    lateinit var test3Btn: Button
    lateinit var test4Btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_test5)

            title = "안녕하세요. 코트린입니다."

            test1Btn = findViewById(R.id.test5Btn1)
            test2Btn = findViewById(R.id.test5Btn2)
            test3Btn = findViewById(R.id.test5Btn3)
            test4Btn = findViewById(R.id.test5Btn4)

            test1Btn.setOnClickListener { it: View ->
                val intent: Intent = Intent(this@TestActivity5, TestActivity6::class.java)
                intent.putExtra("title", title)
                startActivity(intent)
            }

            test2Btn.setOnClickListener { it: View ->
                val intent: Intent = Intent(this@TestActivity5, TestActivity7::class.java)
                startActivity(intent)
            }

            test3Btn.setOnClickListener { it: View ->
                val intent: Intent = Intent(this@TestActivity5, TestActivity4::class.java)
                startActivity(intent)
            }

            test4Btn.setOnClickListener { it: View ->
                val intent: Intent = Intent(this@TestActivity5, TestActivity3::class.java)
                startActivity(intent)
            }

        }

    }