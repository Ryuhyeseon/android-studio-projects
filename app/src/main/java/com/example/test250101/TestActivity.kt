package com.example.test250101

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val testBtn1 : Button = findViewById(R.id.testBtn1)
        val testBtn2 : Button = findViewById(R.id.testBtn2)

        testBtn1.setOnClickListener {
            val toast = Toast.makeText(this, "1번 testBtn", Toast.LENGTH_SHORT)
            toast.show()
//            val toast = Toast.makeText(this@TestActivity, "1번 testBtn", Toast.LENGTH_SHORT)
//            toast.show()
//            Toast.makeText(this, "1번 testBtn", Toast.LENGTH_SHORT).show()
        }

        testBtn2.setOnClickListener {
            showToast()
        }
    }

    // 버튼 콜백 함수 선언
    fun showToast(){
        val toast = Toast.makeText(this, "2번 testBtn", Toast.LENGTH_SHORT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { // API 레벨 30(Android 11) 이상에서만 실행되도록 조건 추가
            toast.addCallback(
                object  : Toast.Callback(){
                    override fun onToastHidden() {
                        super.onToastHidden()
                        println("ToastHidden")
                    }

                    override fun onToastShown() {
                        super.onToastShown()
                        println("ToastShown")
                    }

                }
            )
        }
        toast.show()
    }

}