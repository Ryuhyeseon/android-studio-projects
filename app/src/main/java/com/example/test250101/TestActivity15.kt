package com.example.test250101

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SubscriptionManager
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test250101.databinding.ActivityTest15Binding

class TestActivity15 : AppCompatActivity() {

    lateinit var binding: ActivityTest15Binding
    var phoneNumber:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTest15Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        binding.test15Btn1.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                telephonyManager.registerTelephonyCallback(
                    mainExecutor,
                    object: TelephonyCallback(), TelephonyCallback.CallStateListener{
                        override fun onCallStateChanged(state: Int) {
                            when(state){
                                TelephonyManager.CALL_STATE_IDLE->{
                                    Log.d("amrs", "IDLE")
                                }
                                TelephonyManager.CALL_STATE_OFFHOOK->{
                                    Log.d("amrs", "OFFHOOK")
                                }
                                TelephonyManager.CALL_STATE_RINGING->{
                                    Log.d("amrs", "RINGING")
                                }
                            }
                        }
                    }
                )
                Toast.makeText(this@TestActivity15, "전화 정보 가져오기가 실행되었습니다.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@TestActivity15, "버전이 낮아서 실행이 되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.test15Btn2.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val subscriptionManager =
                    getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS)
                    == PackageManager.PERMISSION_GRANTED) {

                    val subscriptionList = subscriptionManager.activeSubscriptionInfoList
                    if (!subscriptionList.isNullOrEmpty()) {
                        val phone = subscriptionManager.getPhoneNumber(subscriptionList[0].subscriptionId)
                        Toast.makeText(this, "전화번호: $phone", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "활성화된 유심 정보가 없습니다.", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_PHONE_NUMBERS),
                        1001
                    )
                }
            } else {
                Toast.makeText(this, "API 33 이상에서만 지원됩니다.", Toast.LENGTH_SHORT).show()
            }
        }

        }
    }