package com.example.celiacbite

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import roomDatabase.Db
import roomDatabase.entity.Recetas
import androidx.activity.result.ActivityResult

class RegistrarReceta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_receta)

        //INICIO DE BOTONES Y TEXT INPUT LAYOUT
        val til_reg_name_receta = findViewById<TextInputLayout>(R.id.til_edit_reg_receta)
        val til_reg_ingredientes = findViewById<TextInputLayout>(R.id.til_reg_ingredientes)
        val til_reg_instrucciones = findViewById<TextInputLayout>(R.id.til_reg_instrucciones)
        val sp_reg_tipo_alimento = findViewById<Spinner>(R.id.sp_reg_tipo_alimento)
        val btn_save = findViewById<Button>(R.id.btn_save)
        val btn_fotoPlato= findViewById<Button>(R.id.btn_fotoPlato)

        // OBTENCION DEL VALOR INTENT DEL MAIL QUE SE REGISTRO
        val mail:String = intent.getStringExtra("mail").toString()

        // INICIALIZAR LA BASE DE DATOS LOCAL.
        val room = Room.databaseBuilder(this, Db::class.java, "database-ciisa.db").allowMainThreadQueries().build()

        val arrayAdapterSpinner: ArrayAdapter<*>
        val Tipo_receta = arrayOf("Tradicional","Vegetariano","Vegano")
        arrayAdapterSpinner = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Tipo_receta)
        sp_reg_tipo_alimento.adapter = arrayAdapterSpinner

        btn_save.setOnClickListener {
            if (validarCampos()==0){
                var nombre_receta  = til_reg_name_receta.editText?.text.toString()
                var ingredientes = til_reg_ingredientes.editText?.text.toString()
                var instrucciones = til_reg_instrucciones.editText?.text.toString()
                var tipo_alimento = sp_reg_tipo_alimento.selectedItem.toString()
                var id:Long = 0
                //CREAMOS EL OBJETO
                val receta = Recetas(nombre_receta,tipo_alimento,ingredientes,instrucciones,mail)
                //INSERTAMOS LA INFORMACION
                lifecycleScope.launch{
                    id = room.daoRecetas().agregarReceta(receta)
                    if(id>0){
                        Log.d("IDreceta",id.toString())

                        //LISTAR ELEMENTOS AÑADIDOS POR EL USUARIO mail (OPCIONAL)
                        val response = room.daoRecetas().obtenerRecetasUsuario(mail)
                        for(r in response){
                            println(r.toString())
                        }

                        Toast.makeText(this@RegistrarReceta,"Receta registrada exitosamente", Toast.LENGTH_LONG)
                        val intent = Intent(this@RegistrarReceta,Bienvenido::class.java)
                        intent.putExtra("mail",mail)
                        startActivity(intent)
                    }
                }

            }
        }


        btn_fotoPlato.setOnClickListener {
            val intentPhoto = (Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            intent.putExtra("mail",mail )
            asignarFoto2.launch(intentPhoto)
        }

    }

    private val asignarFoto2 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
            result : ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            val imageBitmap = intent?.extras?.get("data") as Bitmap
            val imageView= findViewById<ImageView>(R.id.plato)
            imageView.setImageBitmap(imageBitmap)
        }
    }

    fun validarCampos():Int{
        val til_reg_name_receta= findViewById<TextInputLayout>(R.id.til_edit_reg_receta)
        val til_reg_ingredientes= findViewById<TextInputLayout>(R.id.til_reg_ingredientes)
        val til_reg_instrucciones= findViewById<TextInputLayout>(R.id.til_reg_instrucciones)
        var contador:Int = 0

        var nombre_receta = til_reg_name_receta.editText?.text.toString()
        var ingredientes = til_reg_ingredientes.editText?.text.toString()
        var instrucciones = til_reg_instrucciones.editText?.text.toString()
        val validate = Validacion()

        if(validate.validacionNulo(nombre_receta)){
            til_reg_name_receta.error = "este campo es requerido"
            contador++
        }
        else{
            til_reg_name_receta.error = ""
        }
        if(validate.validacionNulo(ingredientes)){
            til_reg_ingredientes.error = "este campo es requerido"
            contador++
        }
        else{
            til_reg_ingredientes.error = ""
        }
        if(validate.validacionNulo(instrucciones)){
            til_reg_instrucciones.error = "este campo es requerido"
            contador++
        }
        return contador
    }

}
