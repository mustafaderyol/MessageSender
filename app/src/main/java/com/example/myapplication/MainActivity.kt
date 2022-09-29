package com.example.myapplication

import android.Manifest
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    private val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val deniedPermission = permissions.keys.find { key ->
            permissions[key] != true
        }
        if (deniedPermission != null) {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermission.launch(arrayOf(Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_SMS,
                Manifest.permission.SEND_SMS,
            Manifest.permission.READ_PHONE_STATE))
    }
}
