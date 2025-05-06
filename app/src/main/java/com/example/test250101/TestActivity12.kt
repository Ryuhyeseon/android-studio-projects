package com.example.test250101

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test250101.databinding.ActivityTest12Binding

class TestActivity12 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest12Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = openOrCreateDatabase("testdb", Context.MODE_PRIVATE, null)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 테이블 생성
        binding.test12btn1.setOnClickListener {
            try {
                db.execSQL(
                    "create table USER_TB(" +
                            "id integer primary key autoincrement, " +
                            "name text not null, " +
                            "phone text not null)"
                )
                val msg = "테이블 생성 완료"
                Log.d("amrs", msg)
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                val err = "테이블 생성 실패: ${e.message}"
                Log.e("amrs", err)
                Toast.makeText(this, err, Toast.LENGTH_SHORT).show()
            }
        }

        // 데이터 삽입
        binding.test12btn2.setOnClickListener {
            try {
                db.execSQL(
                    "insert into USER_TB(name, phone) values(?, ?)",
                    arrayOf("류혜선", "010-2364-2536")
                )
                val msg = "데이터 입력 완료"
                Log.d("amrs", msg)
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                val err = "입력 실패: ${e.message}"
                Log.e("amrs", err)
                Toast.makeText(this, err, Toast.LENGTH_SHORT).show()
            }
        }

        // 데이터 조회
        binding.test12btn3.setOnClickListener {
            try {
                val cursor = db.rawQuery("select * from USER_TB", null)
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(0)
                    val name = cursor.getString(1)
                    val phone = cursor.getString(2)
                    val msg = "아이디: $id, 이름: $name, 연락처: $phone"
                    Log.d("amrs", msg)
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                }
                cursor.close()
            } catch (e: Exception) {
                val err = "조회 실패: ${e.message}"
                Log.e("amrs", err)
                Toast.makeText(this, err, Toast.LENGTH_SHORT).show()
            }
        }

        // 데이터 삭제
        binding.test12btn4.setOnClickListener {
            try {
                db.execSQL("delete from USER_TB")
                val msg = "데이터 전체 삭제 완료"
                Log.d("amrs", msg)
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                val err = "데이터 삭제 실패: ${e.message}"
                Log.e("amrs", err)
                Toast.makeText(this, err, Toast.LENGTH_SHORT).show()
            }
        }

        // 테이블 삭제
        binding.test12btn5.setOnClickListener {
            try {
                db.execSQL("drop table if exists USER_TB")
                val msg = "테이블 삭제 완료"
                Log.d("amrs", msg)
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                val err = "삭제 실패: ${e.message}"
                Log.e("amrs", err)
                Toast.makeText(this, err, Toast.LENGTH_SHORT).show()
            }
        }

    }
}
