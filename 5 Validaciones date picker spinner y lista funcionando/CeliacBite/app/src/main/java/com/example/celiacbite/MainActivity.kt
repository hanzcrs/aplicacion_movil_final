package com.example.celiacbite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import roomDatabase.Db

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // SE INICIA LA BASE DE DATOS ROOM-DATABASE
        val room = Room.databaseBuilder(this, Db::class.java, "database-ciisa.db").allowMainThreadQueries().build()

        //INICIO DE BOTONES Y TEXT INPUT LAYOUT
        val til_mail = findViewById<TextInputLayout>(R.id.til_user)
        val til_pass = findViewById<TextInputLayout>(R.id.til_password)
        val btnInicio = findViewById<Button>(R.id.buttonInicio)
        val btnOlvide = findViewById<Button>(R.id.btn_olvide)
        val btnRegistrarse= findViewById<Button>(R.id.ButtonRegistrarse)

        btnOlvide.setOnClickListener {
            val intent: Intent = Intent(this, MenuOlvideContra:: class.java)
            startActivity(intent)
        }
        btnRegistrarse.setOnClickListener {
            val intent: Intent = Intent(this, MenuRegistrate:: class.java)
            startActivity(intent)
        }
        btnRegistrarse.setOnClickListener {
            val intent = Intent(this@MainActivity, MenuRegistrate::class.java)
            startActivity(intent)
        }

        // CORRUTINA PARA OBTENER USUARIO
        lifecycleScope.launch{
            val response = room.daoUsuario().obtenerUsuarios()
            for (user in response){
                println(user.toString())
            }
        }

        // INICIO DE VARIABLES Y VALIDACION DE USUARIO POR PRIMER INTENT SE PASA
        // PUTEXTRA EL NOMBRE DE USUARIO DATO QUE NECESITAMOS PARA PASAR POR INTENT.
        btnInicio.setOnClickListener {
            var mail = til_mail.editText?.text.toString()
            var pass = til_pass.editText?.text.toString()
            if (validarCampos()==0){
              // PARA PASAR Y VERIFICAR POR CONSOLA: println("$mail $pass")
       // LOGIN DE USUARIO
           lifecycleScope.launch{
               val response = room.daoUsuario().login(mail,pass)
                  if(response.size == 1){
                    til_mail.error = ""
                    til_pass.error = ""
                    val intent: Intent = Intent(this@MainActivity, Bienvenido:: class.java)
                    intent.putExtra("mail",mail )
                    startActivity(intent)
                    }
                    else{
                        til_mail.error = "correo o contraseña invalida"
                        til_pass.error = "correo o contraseña incorrecta"
                    }
                }
            }
        }
    }
    // FUNCION DE VALIDACION DE CAMPOS
    fun validarCampos():Int{
        var contador:Int =0
        val til_mail = findViewById<TextInputLayout>(R.id.til_user)
        val til_pass = findViewById<TextInputLayout>(R.id.til_password)
        var mail = findViewById<TextInputLayout>(R.id.til_user).editText?.text.toString()
        var pass = findViewById<TextInputLayout>(R.id.til_password).editText?.text.toString()
        val validacion = Validacion()
        if(validacion.validacionNulo(mail)){
            til_mail.error = "este campo es requerido"
            contador++
                 }
        else {
            if (validacion.validarCorreo(mail)){
                til_mail.error = "el correo notiene el formato correcto"
                contador++
            }
            else {
                til_mail.error = ""
            }
        }
        if (validacion.validacionNulo(pass)){
            til_pass.error = "este campo es requerido"
            contador++
        }
        else {
            til_pass.error = ""
        }
        return contador
    }
    override fun onStart() {
        super.onStart()
        Log.i("CICLO_DE_VIDA","onStart()")
    }
    override fun onResume() {
        super.onResume()
        Log.i("CICLO_DE_VIDA","onResume()")
    }
    override fun onPause() {
        super.onPause()
        Log.i("CICLO_DE_VIDA","onPause()")
    }
    override fun onStop() {
        super.onStop()
        Log.i("CICLO_DE_VIDA","onStop()")
    }
    override fun onRestart(){
        super.onRestart()
        Log.i("CICLO_DE_VIDA","onRestart()")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.i("CICLO_DE_VIDA","onDestroy()")
    }
}

