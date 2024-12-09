package com.example.playlistmaker

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.playlistmaker.R.id.learn_word




class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_learn_word)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(learn_word)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets}
        val image = findViewById<ImageButton>(R.id.number1)
        image.setOnClickListener{
            Toast.makeText(this@MainActivity, "Переход в поиск ITunes!", Toast.LENGTH_SHORT).show()

        }
        val image2 = findViewById<ImageButton>(R.id.number3)
        image2.setOnClickListener {
            val image2Intent = Intent (this, Settings::class.java)
            startActivity(image2Intent)



            }
        }

    fun toogleDarkMode(view: View) {}


}




