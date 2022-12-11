package com.example.celiacbite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuOlvideContra : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_olvide_contra)

        val btnBackOlvide: Button = findViewById(R.id.ButtonBackOlvidaste)
        btnBackOlvide.setOnClickListener {
            val intent: Intent= Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }
    }
}