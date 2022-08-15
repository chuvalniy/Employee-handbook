package com.example.kode_test_app

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_KodeTestApp)

        setContentView(R.layout.activity_main)

        val window: Window = window
        WindowInsetsControllerCompat(window,window.decorView).isAppearanceLightStatusBars = true
    }
}