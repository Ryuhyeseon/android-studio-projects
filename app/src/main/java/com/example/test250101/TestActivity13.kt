package com.example.test250101

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test250101.databinding.ActivityTest13Binding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class TestActivity13 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest13Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val file = File(filesDir, "test.txt")
        val writeStream: OutputStreamWriter = file.writer()
        val readStream: BufferedReader = file.reader().buffered()

        binding.test13btn1.setOnClickListener {
            writeStream.write("hello world!")
            writeStream.flush()
        }

        binding.test13btn2.setOnClickListener {
            readStream.forEachLine {
                Toast.makeText(this@TestActivity13, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}