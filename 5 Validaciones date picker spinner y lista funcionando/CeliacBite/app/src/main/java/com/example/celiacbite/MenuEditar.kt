package com.example.celiacbite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.launch
import roomDatabase.Db
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.material.textfield.TextInputLayout


class MenuEditar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_editar)


        // INICIALIZAR LA BASE DE DATOS LOCAL.
        val room = Room.databaseBuilder(this, Db::class.java, "database-ciisa.db").allowMainThreadQueries().build()

        val til_editNombre = findViewById<TextInputLayout>(R.id.til_editNombre)
        val til_edit_instrucciones = findViewById<TextInputLayout>(R.id.til_edit_instrucciones)
        val til_edit_ingredientes = findViewById<TextInputLayout>(R.id.til_edit_ingredientes)
        val tv_id = findViewById<TextView>(R.id.tv_id)
        val btn_edit = findViewById<Button>(R.id.btn_actualizar)
        val btn_delete = findViewById<Button>(R.id.btn_eliminar)
        val btn_backEditar = findViewById<Button>(R.id.btn_backEditar)
        var tipo_alimento=""

        val mail:String = intent.getStringExtra("mail").toString()
        val objeto_receta:String = intent.getStringExtra("objeto_receta").toString()

        btn_edit.setOnClickListener {
            val intent: Intent = Intent(this, MenuRecetas::class.java)
            intent.putExtra("mail",mail )
            startActivity(intent)
        }

        btn_backEditar.setOnClickListener {
            val intent: Intent = Intent(this, MenuRecetas::class.java)
            intent.putExtra("mail",mail)
            startActivity(intent)
        }

        lifecycleScope.launch{
            val respuesta = room.daoRecetas().obtenerRecetasNombre(objeto_receta,mail)
            if (respuesta.size == 1){
                for(elemnt in respuesta){
                    til_editNombre.editText?.setText(elemnt.nombre_receta.toString())
                    til_edit_instrucciones.editText?.setText(elemnt.instrucciones.toString())
                    til_edit_ingredientes.editText?.setText(elemnt.ingredientes.toString())
                    tv_id.setText(elemnt.id.toString())
                    tipo_alimento = elemnt.tipo_alimento.toString()
                }
            }
        }

        btn_edit.setOnClickListener {
            lifecycleScope.launch{
                var nombre = til_editNombre.editText?.text.toString()
                var intruccion = til_edit_instrucciones.editText?.text.toString()
                var ingrediente = til_edit_ingredientes.editText?.text.toString()
                var id = tv_id.text.toString()
                val respuesta = room.daoRecetas().actualizarReceta(nombre,tipo_alimento
                    ,ingrediente,intruccion,id.toLong()
                )
                println(respuesta)
                val intent = Intent(this@MenuEditar,MenuRecetas::class.java)
                intent.putExtra("mail",mail)
                startActivity(intent)
            }
        }
        btn_delete.setOnClickListener {
            lifecycleScope.launch{
                var id = tv_id.text.toString()
                val respuesta = room.daoRecetas().borrarReceta(id.toLong())
                println(respuesta)
                val intent = Intent(this@MenuEditar,MenuRecetas::class.java)
                intent.putExtra("mail",mail)
                startActivity(intent)
            }
        }

    }
}