package com.example.test250101

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.test250101.databinding.ActivityTest17Binding
import com.google.android.gms.location.*

class TestActivity17 : AppCompatActivity() {

    private lateinit var binding: ActivityTest17Binding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var locationCallback: LocationCallback? = null
    private var requestingLocationUpdates = false

    companion object {
        private const val TAG = "MainActivityLocation"
    }

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                Toast.makeText(this, "정밀 위치 권한이 허용되었습니다.", Toast.LENGTH_SHORT).show()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                Toast.makeText(this, "대략적인 위치 권한만 허용되었습니다.", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "위치 권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTest17Binding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.btnGetLastLocation.setOnClickListener {
            getLastKnownLocation()
        }

        binding.btnRequestLocationUpdates.setOnClickListener {
            if (!requestingLocationUpdates) {
                startLocationUpdates()
            } else {
                Toast.makeText(this, "이미 위치 업데이트 중입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnStopLocationUpdates.setOnClickListener {
            stopLocationUpdates()
        }
    }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermissions() {
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {
        if (checkLocationPermission()) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        val text = "마지막 위치:\n위도: ${location.latitude}, 경도: ${location.longitude}\n" +
                                "정확도: ${location.accuracy}m, 시간: ${location.time}"
                        binding.tvLocationInfo.text = text
                        Log.d(TAG, "Last known location: $text")
                    } else {
                        binding.tvLocationInfo.text = "마지막 위치를 가져올 수 없습니다. (위치 기록 없음)"
                        Log.d(TAG, "Last known location is null.")
                        Toast.makeText(this, "마지막 위치 정보가 없습니다. 위치 업데이트를 시도해보세요.", Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener { e ->
                    binding.tvLocationInfo.text = "마지막 위치 가져오기 실패"
                    Log.e(TAG, "Failed to get last known location", e)
                    Toast.makeText(this, "오류: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            requestLocationPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        if (checkLocationPermission()) {
            val locationRequest = LocationRequest.create().apply {
                interval = 10000
                fastestInterval = 5000
                priority = Priority.PRIORITY_HIGH_ACCURACY
            }

            locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    val location = locationResult.lastLocation
                    if (location != null) {
                        val text = "업데이트된 위치:\n위도: ${location.latitude}, 경도: ${location.longitude}\n" +
                                "정확도: ${location.accuracy}m, 시간: ${location.time}"
                        binding.tvLocationInfo.text = text
                        Log.d(TAG, "Updated location: $text")
                    }
                }
            }

            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback as LocationCallback,
                mainLooper
            )

            requestingLocationUpdates = true
            Toast.makeText(this, "위치 업데이트 시작됨", Toast.LENGTH_SHORT).show()
        } else {
            requestLocationPermissions()
        }
    }

    private fun stopLocationUpdates() {
        if (requestingLocationUpdates && locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback!!)
            requestingLocationUpdates = false
            Toast.makeText(this, "위치 업데이트 중지됨", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocationUpdates()
    }
}
