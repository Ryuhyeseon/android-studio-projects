package com.example.test250101

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test250101.databinding.ActivityTest11Binding

class TestActivity11 : AppCompatActivity() {

    lateinit var binding: ActivityTest11Binding
    lateinit var requestContactsLaunchar:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTest11Binding.inflate(layoutInflater)
        setContentView(binding.root)

        requestContactsLaunchar = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
             if(it.resultCode == RESULT_OK){
                 Log.d("amrs", "${it.data?.data}")
             }
        }

        binding.test11btn1.setOnClickListener{
            Toast.makeText(this@TestActivity11, "use binding", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
            requestContactsLaunchar.launch(intent)
        }

        binding.test11btn2.setOnClickListener{
            val intent: Intent = Intent(this@TestActivity11, GalleryActivity::class.java)
            startActivity(intent)
        }
    }
}