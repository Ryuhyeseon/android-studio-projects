package com.example.test250101

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    override fun onCreate() {
        super.onCreate()
        Log.d("omrs", "서비스 onCreate()")
    }

    // 서비스 안에 바인더 정의
    inner class LocalBinder : Binder() {
        fun getService(): MyService = this@MyService
    }

    private val binder = LocalBinder()

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("omrs", "서비스 실행")
        return binder  // 바인딩 성공
    }

}