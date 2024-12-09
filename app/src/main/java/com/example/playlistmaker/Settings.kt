package com.example.playlistmaker

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class Settings : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("isDarkMode", false)

        setThemeAccordingToPreference(isDarkMode)

        setContentView(R.layout.activity_settings)

        val themeToggleButton = findViewById<ImageButton>(R.id.butlist)
        themeToggleButton.setOnClickListener {
            val newMode = !sharedPreferences.getBoolean("isDarkMode", false)
            saveThemePreference(newMode)
            updateTheme(newMode)
        }

        val shareButton = findViewById<ImageButton>(R.id.butshare)
        shareButton.setOnClickListener {
            shareText("Оцени мой проект")
        }

        val supportButton = findViewById<ImageButton>(R.id.butsup)
        supportButton.setOnClickListener {
            val supportIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("vitalij.sotnikov2000@mail.ru"))
            }
            startActivity(supportIntent)
        }

        val agreementButton = findViewById<ImageButton>(R.id.butpol)
        agreementButton.setOnClickListener {
            val agreementIntent = Intent(this, ActivityMessage::class.java)
            startActivity(agreementIntent)
        }

        val backButton = findViewById<ImageButton>(R.id.but_back)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun shareText(text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Выберите приложение для отправки"))
        }
    }

    private fun saveThemePreference(isDarkMode: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isDarkMode", isDarkMode)
        editor.apply()
    }

    private fun updateTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            Toast.makeText(this, "Тёмная тема включена", Toast.LENGTH_SHORT).show()
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Toast.makeText(this, "Светлая тема включена", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setThemeAccordingToPreference(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
