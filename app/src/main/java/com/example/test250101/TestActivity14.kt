package com.example.test250101

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test250101.databinding.ActivityTest13Binding
import com.example.test250101.databinding.ActivityTest14Binding

class TestActivity14 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest14Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pref = getSharedPreferences("com.example.test250101", Context.MODE_PRIVATE)

        binding.test14btn1.setOnClickListener {
            pref.edit().run {
                putString("data1", "ABC")
                putInt("data2", 123)
                commit()
            }
            pref.edit().putString("data3", "DEF").apply()
        }

        binding.test14btn2.setOnClickListener {
            val data1 = pref.getString("data1", "")
            val data2 = pref.getInt("data2", 0)
            val data3 = pref.getString("data3", "")
            val data4 = pref.getBoolean("data4", true)
            Log.d("amrs", "data1:${data1} data2:${data2}",)
            Toast.makeText(this@TestActivity14, "data1:${data1} data2:${data2}", Toast.LENGTH_SHORT).show()
            Toast.makeText(this@TestActivity14, "${data1} ${data3}", Toast.LENGTH_SHORT).show()
            Toast.makeText(this@TestActivity14, "스위치 상태 ${data4}", Toast.LENGTH_SHORT).show()
        }

        binding.test14switch1.setOnCheckedChangeListener { compoundButton, b ->
            if(b){
                Toast.makeText(this@TestActivity14, "스위치 on", Toast.LENGTH_SHORT).show()
                pref.edit().putBoolean("data4", true).apply()
            }else{
                Toast.makeText(this@TestActivity14, "스위치 off", Toast.LENGTH_SHORT).show()
                pref.edit().putBoolean("data4", false).apply()
            }
        }

    }
}