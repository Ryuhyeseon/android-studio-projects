package com.example.test250101

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.test250101.databinding.ActivityTest16Binding

class TestActivity16 : AppCompatActivity() {
    lateinit var binding: ActivityTest16Binding

    // 예시 URL 설정 (실제 요청할 API 주소로 변경)
    var url: String = "https://jsonplaceholder.typicode.com/posts/1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ViewBinding 설정
        binding = ActivityTest16Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 시스템 바(상단바, 하단바) padding 적용
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Volley 네트워크 요청 버튼
        binding.test16Btn1.setOnClickListener {
            // 문자열 응답을 받아오는 GET 요청
            val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                Response.Listener { response ->
                    Log.d("amrs", "String Response: $response")
                },
                Response.ErrorListener { error ->
                    Log.d("amrs", "Volley Error: $error")
                }
            )

            // Volley 요청 큐에 등록
            val queue = Volley.newRequestQueue(this)
            queue.add(stringRequest)
        }
    }
}