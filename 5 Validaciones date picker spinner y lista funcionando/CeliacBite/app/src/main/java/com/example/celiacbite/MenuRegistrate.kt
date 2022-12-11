package com.example.celiacbite

import android.app.DatePickerDialog
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
import roomDatabase.entity.Usuario
import java.util.*

class MenuRegistrate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_registrate)
        val til_re_mail = findViewById<TextInputLayout>(R.id.til_re_mail)
        val til_re_name = findViewById<TextInputLayout>(R.id.til_re_name)
        val til_re_pass = findViewById<TextInputLayout>(R.id.til_re_pass)
        val til_dateb = findViewById<TextInputLayout>(R.id.til_dateb)
        val btn_re_register = findViewById<Button>(R.id.btn_re_register)

        val cal = Calendar.getInstance()

        // INICIAR BD
        val room = Room.databaseBuilder(this, Db::class.java, "database-ciisa.db").allowMainThreadQueries().build()

        //DATEPIKER
        val listenerFecha = DatePickerDialog.OnDateSetListener { datePicker, anyo, mes, dia ->
            til_dateb.editText?.setText("$dia/$mes/$anyo")
        }
        til_dateb.editText?.setOnClickListener {
            DatePickerDialog(this, listenerFecha,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }



        //Obtener valores a insertar en los campos
        btn_re_register.setOnClickListener {
            if (validarCampos()==0){
                var mail = til_re_mail.editText?.text.toString()
                var name = til_re_name.editText?.text.toString()
                var pass = til_re_pass.editText?.text.toString()
                var til_dateb = til_dateb.editText?.text.toString()
                var id:Long = 0
                // Creamos el usuario OBJETO
                val usuario = Usuario(mail,name,pass,til_dateb)
                // INSERTAMOS LA INFORMACION
                lifecycleScope.launch {
                    id = room.daoUsuario().agregarUsuario(usuario)
                        if(id>0){
                            Log.d("IDuser",id.toString())
                            Toast.makeText(this@MenuRegistrate,"usuario registrado correctamente",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@MenuRegistrate,MainActivity::class.java)
                            startActivity(intent)
                          }
                    }
           }
       }
    }

    fun validarCampos():Int {
        var contador: Int = 0
        val til_re_mail = findViewById<TextInputLayout>(R.id.til_re_mail)
        val til_re_name = findViewById<TextInputLayout>(R.id.til_re_name)
        val til_re_pass = findViewById<TextInputLayout>(R.id.til_re_pass)
        val til_re_rpass = findViewById<TextInputLayout>(R.id.til_re_rpass)
        val til_dateb = findViewById<TextInputLayout>(R.id.til_dateb)
        var mail = til_re_mail.editText?.text.toString()
        var name = til_re_name.editText?.text.toString()
        var pass = til_re_pass.editText?.text.toString()
        var rpass = til_re_rpass.editText?.text.toString()
        var date = til_dateb.editText?.text.toString()

        val validacion = Validacion()
        if(validacion.validacionNulo(mail)){
            til_re_mail.error = "este campo es requerido"
            contador++
        }
        else {
            if (validacion.validarCorreo(mail)){
                til_re_mail.error = "el correo notiene el formato correcto"
                contador++
            }
            else {
                til_re_mail.error = ""
            }
        }
        if (validacion.validacionNulo(pass)){
            til_re_pass.error = "este campo es requerido"
            contador++
        }
        else {
            til_re_pass.error = ""
        }
        if(validacion.validacionNulo(name)){
            til_re_name.error = "este campo es requerido"
            contador++
            }
        else{
            til_re_name.error = ""
        }

        if (validacion.validacionNulo(rpass)){
            til_re_rpass.error = "este campo es requerido"
            contador++
        }
        else {
            til_re_rpass.error = ""
        }
        if (validacion.validacioncamposIguales(pass,rpass)){
            til_re_pass.error = "Las contraseñas deben ser iguales"
            til_re_rpass.error = "Las contraseñas deben ser iguales"
            contador++
        }
        else {
            til_re_pass.error = ""
            til_re_rpass.error = ""
        }
        if (validacion.validacionNulo(date)){
            til_dateb.error = "este campo es requerido"
            contador++
        }
        else {
            til_dateb.error = ""
        }
        return contador
    }
}
