package com.example.test250101

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TestActivity10 : AppCompatActivity() {

    lateinit var btn1:Button
    lateinit var btn2:Button
    lateinit var btn3:Button
    lateinit var btn4:Button

    // startService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test10)

        // 서비스를 시작하는 버튼
        btn1 = findViewById(R.id.test10Btn1)
        btn1.setOnClickListener {
            val intent : Intent = Intent(this@TestActivity10, MyService::class.java)
            startService(intent)
            Log.d("omrs", "test10Btn1 서비스 실행 버튼")
        }

        // 서비스를 중지하는 버튼
        btn2 = findViewById(R.id.test10Btn2)
        btn2.setOnClickListener {
            val intent : Intent = Intent(this@TestActivity10, MyService::class.java)
            stopService(intent)
            Log.d("omrs", "test10Btn2 : 서비스 중지 버튼")
        }

        // bindService()
        val connection : ServiceConnection = object:ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d("omrs", "onServiceConnected")
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d("omrs", "onServiceDisconnected")
            }
        }

        btn3 = findViewById(R.id.test10Btn3)
        btn3.setOnClickListener {
            val intent : Intent = Intent(this@TestActivity10, MyService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
            Log.d("omrs", "test10Btn3 : 서비스 시작 버튼")
        }

        btn4 = findViewById(R.id.test10Btn4)
        btn4.setOnClickListener {
            val intent : Intent = Intent(this@TestActivity10, MyService::class.java)
            unbindService(connection)
            Log.d("omrs", "test10Btn4 : 서비스 중지 버튼")
        }

    }
}