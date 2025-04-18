package com.example.test250101

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class TestActivity9 : AppCompatActivity() {

    // BroadcastReceiver 인스턴스 선언
    private lateinit var receiver: BroadcastReceiver
    // IntentFilter 인스턴스 선언
    private lateinit var filter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test9)

        // 리시버 인스턴스 생성
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d("omrs", "onReceive called with action: ${intent?.action}") // onReceive 호출 자체 확인
                when(intent?.action){
                    Intent.ACTION_SCREEN_ON -> Log.d("omrs", ">>> screen on <<<") // 좀 더 눈에 띄게
                    Intent.ACTION_SCREEN_OFF -> Log.d("omrs", "<<< screen off >>>") // 좀 더 눈에 띄게
                }
            }
        }

        // 인텐트 필터 생성 및 액션 추가
        filter = IntentFilter(Intent.ACTION_SCREEN_ON).apply {
            addAction(Intent.ACTION_SCREEN_OFF)
        }

        // 중요: onCreate에서는 등록하지 않습니다.
        // Log.d("omrs", "onCreate finished, receiver created but not registered yet.")
    }

    /**
     * 액티비티가 사용자와 상호작용을 시작할 준비가 되었을 때 호출됩니다.
     * 이 시점에서 리시버를 등록합니다.
     */
    override fun onResume() {
        super.onResume()
        // onResume에서 리시버 등록
        registerReceiver(receiver, filter)
        Log.d("omrs", "Receiver registered in onResume")
    }

    /**
     * 액티비티가 포커스를 잃고 일시정지 상태로 들어갈 때 호출됩니다.
     * 이 시점에서 리시버를 해제합니다.
     */
    override fun onPause() {
        super.onPause()
        // onPause에서 리시버 해제
        unregisterReceiver(receiver)
        Log.d("omrs", "Receiver unregistered in onPause")
    }

    // onDestroy는 생략 가능 (onPause에서 이미 해제되므로)
}