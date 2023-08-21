package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class splash_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        Handler(Looper.getMainLooper()).postDelayed({
            if (sharedPreferences.getBoolean("IS_LOGGED_IN", false)) {
                // If the user is logged in, navigate to the home activity
                startActivity(Intent(this, MainPageActivity::class.java))

            } else {
                // If the user is not logged in, navigate to the login activity
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }, 2000)
    }
}
