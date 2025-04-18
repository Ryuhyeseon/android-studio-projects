package com.example.test250101

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test250101.common.MyReceiver

class TestActivity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test7)

        val receiver1:MyReceiver = MyReceiver()

        // receiver 실행
        val intent:Intent = Intent(this, MyReceiver::class.java)

        // 새로 만든 receiver
        val receiver2 = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
            }
        }

        // receiver 등록
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(receiver2, IntentFilter("ACTION RECEIVER"), RECEIVER_EXPORTED)
        }

        // receiver 해제
        unregisterReceiver(receiver2)
        
    }
}