package com.example.test250101

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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

        val testBtn1: Button = findViewById(R.id.testBtn1)
        val testBtn2: Button = findViewById(R.id.testBtn2)
        val testBtn3: Button = findViewById(R.id.testBtn3)
        val testBtn4: Button = findViewById(R.id.testBtn4)
        val testBtn5: Button = findViewById(R.id.testBtn5)

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

        testBtn3.setOnClickListener {
            DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    TODO("Not yet implemented")
                }
            }, 2025, 1, 14).show()
        }

        testBtn4.setOnClickListener {
            TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    TODO("Not yet implemented")
                }
            }, 15, 0, true).show()
        }

        testBtn5.setOnClickListener{
            AlertDialog.Builder(this).run {
                setTitle("AlertDialog")
                setIcon(R.mipmap.ic_launcher_round)
                setMessage("Exit?")
                setPositiveButton("Ok", null)
                setNegativeButton("Cancle", null)
                setNeutralButton("More", null)
                show()
            }
        }

    }

    // 버튼 클릭 후 토스트 알림 콜백 함수 선언
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