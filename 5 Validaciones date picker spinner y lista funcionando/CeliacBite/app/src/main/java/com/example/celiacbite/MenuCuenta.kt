package com.example.celiacbite

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts


class MenuCuenta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_cuenta)

        //INICIO DE BOTONES Y TEXT INPUT LAYOUT
        val txt_user2 = findViewById<TextView>(R.id.txt_user2)
        val btn = findViewById<Button>(R.id.backCuenta)
        val btnCerrar = findViewById<Button>(R.id.CerrarSesion)
        val btn_tomar_foto = findViewById<Button>(R.id.btn_tomar_foto)

        // obtencion de valor mail:String por intent put.extra
        val mail:String = intent.getStringExtra("mail").toString()
        txt_user2.setText(mail)

        btn.setOnClickListener {
            val intent: Intent = Intent(this, Bienvenido::class.java)
            intent.putExtra("mail",mail )
            startActivity(intent)
        }

        btnCerrar.setOnClickListener {

            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_tomar_foto.setOnClickListener {
            val intentPhoto = (Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            intent.putExtra("mail",mail )
            asignarFoto.launch(intentPhoto)

        }

    }
    private val asignarFoto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result : ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            val imageBitmap = intent?.extras?.get("data") as Bitmap
            val imageView = findViewById<ImageView>(R.id.imv_photo)
            imageView.setImageBitmap(imageBitmap)
        }
    }

}