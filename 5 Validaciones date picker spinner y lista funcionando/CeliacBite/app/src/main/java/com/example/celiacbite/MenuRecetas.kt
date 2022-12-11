package com.example.celiacbite
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import roomDatabase.Db

class MenuRecetas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_recetas)

        // OBTENCION DE NOMBRE DE USUARIO QUE INICIO SESION.
        val mail:String = intent.getStringExtra("mail").toString()
        // INICIO DE BASE DE DATOS LOCAL

        val sp_category2 = findViewById<Spinner>(R.id.sp_category2)
        val lv_datos2 = findViewById<ListView>(R.id.lv_datos2)
        val fab_add = findViewById<FloatingActionButton>(R.id.fab_add)
        val room = Room.databaseBuilder(this, Db::class.java, "database-ciisa.db").allowMainThreadQueries().build()

        val btn_backEditar: Button = findViewById(R.id.backRecetas)
        btn_backEditar.setOnClickListener {
            val intent: Intent = Intent(this, Bienvenido::class.java)
            intent.putExtra("mail",mail )
            startActivity(intent)
        }


        //REFERENCIAS DE SPINNER Y LIST VIEW
        //CREACION DEL SPINNER
        val arrayAdapterSpinner: ArrayAdapter<*>
        val tipos_producto = arrayOf("Tradicional", "Vegetariano","Vegano")
        arrayAdapterSpinner = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,tipos_producto)
        sp_category2.adapter = arrayAdapterSpinner

        ///CREACION DE LISTVIEW
        var arrayAdapterListView: ArrayAdapter<*>
        var productos = ArrayList<String>()
        //OBTENCION DE LAS DISTINTAS RECETAS FILTRADA POR USUARIO INICIADO EN APP.
        lifecycleScope.launch {
            //LISTAR RECETAS POR USUARIO
            val response = room.daoRecetas().obtenerRecetasUsuario(mail)
            for(index in response.indices){
                //println(response[index].nombre_receta)
                productos.add(response[index].nombre_receta.toString())
            }
            arrayAdapterListView = ArrayAdapter(this@MenuRecetas,
                android.R.layout.simple_list_item_1,productos)
            lv_datos2.adapter = arrayAdapterListView
        }

        //BOTON DE AÑADIR RECETA QUE LLEVA A LA SIGUIENTE VISTA
        fab_add.setOnClickListener {
            val intent = Intent(this,RegistrarReceta::class.java)
            intent.putExtra("mail",mail)
            startActivity(intent)
        }


        // SELECCION DE RECETA
        lv_datos2.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //Toast.makeText(this@MenuRecetas,"Seleccionaste ${productos[position]}",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MenuRecetas,MenuEditar::class.java)
                intent.putExtra("objeto_receta","${productos[position]}")
                intent.putExtra("mail",mail)
                println("usuario: "+mail+"menu seleccionado: "+"${productos[position]}")
                startActivity(intent)
            }
        }


    }
}