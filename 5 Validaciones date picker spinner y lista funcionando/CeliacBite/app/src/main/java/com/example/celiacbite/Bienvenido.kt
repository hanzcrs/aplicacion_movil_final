package com.example.celiacbite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class Bienvenido : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenido)

        //INICIO DE BOTONES Y TEXT INPUT LAYOUT
        val txt_user = findViewById<TextView>(R.id.txt_user)
        val btn = findViewById<Button>(R.id.back)
        val btnProd = findViewById<Button>(R.id.Productos)
        val btnResets = findViewById<Button>(R.id.Recetas)
        val btnUser = findViewById<ImageButton>(R.id.AccesoUsuario)

        //obtencion intent valor
        val mail:String = intent.getStringExtra("mail").toString()
        txt_user.setText("$mail")

        btn.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnProd.setOnClickListener {
            val intent: Intent = Intent(this, BuscadorProductos::class.java)
            intent.putExtra("mail",mail )
            startActivity(intent)
        }

        btnResets.setOnClickListener {
            val intent: Intent = Intent(this, MenuRecetas::class.java)
            intent.putExtra("mail",mail )
            startActivity(intent)
        }

             btnUser.setOnClickListener {
                 val intent: Intent = Intent(this, MenuCuenta::class.java)
                 intent.putExtra("mail",mail )
                 startActivity(intent)
             }
    }
}