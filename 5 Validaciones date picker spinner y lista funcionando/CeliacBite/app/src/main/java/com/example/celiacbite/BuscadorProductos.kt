package com.example.celiacbite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.*

class BuscadorProductos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscador_productos)

        //INICIO DE BOTONES Y TEXT INPUT LAYOUT
        val btn_atras = findViewById<Button>(R.id.btn_atras)
        val btn_view_map = findViewById<Button>(R.id.btn_map)

        //obtencion intent valor
        val mail:String = intent.getStringExtra("mail").toString()

        btn_atras.setOnClickListener {
            val intent: Intent = Intent(this, Bienvenido::class.java)
            intent.putExtra("mail",mail )
            startActivity(intent)

        }

        btn_view_map.setOnClickListener {
            startActivity(
                Intent(this,
                    MapsActivity::class.java)

            )
        }

        // SE GENERA EL SPINNER Y LISTVIEW
        val sp_category = findViewById<Spinner>(R.id.sp_category)
        val lv_datos = findViewById<ListView>(R.id.lv_datos)

        //REFERENCIAS DE SPINNER Y LIST VIEW
        //CREACION DEL SPINNER SE DEJA CREADO POR DEFECTO LAS DISTINTAS CATEGORIAS.
        val arrayAdapterSpinner: ArrayAdapter<*>
        val tipos_producto = arrayOf("Todas Las Opciones", "Pan y harinas","Jugos y Bebidas","Snacks","Otros")
        arrayAdapterSpinner = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,tipos_producto)
        sp_category.adapter = arrayAdapterSpinner

        ///CREACION DE LISTVIEW
        //Siempre acepta un Array o una Lista como entrada.
        var arrayAdapterListView: ArrayAdapter<*>
        var productos = arrayOf("Pan Tipo Molde","pan Tipo Marraqueta","Pan Tipo Hallulla","Harinas","Jugo Sin Azucar", "Jugos Con Azucar", "Bebidas Light", "Energeticas", "Ramitas", "Galletas","Queques", "Alfajores","Papas Fritas","Condimentos","Avenas","Pastas","Frutas y Verduras")
        arrayAdapterListView = ArrayAdapter(this,R.layout.list_item,productos)
        lv_datos.adapter = arrayAdapterListView
        lv_datos.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@BuscadorProductos,"Seleccionaste ${productos[position]}",Toast.LENGTH_SHORT).show()
            }
        }

        //SPINNER DINAMICO
        // SE CARGARA PARA CADA OPCION LOS PRODUCTOS QUE han CORRESPONDA.
        sp_category.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                var input = sp_category.selectedItem.toString()
                println(input)
                when(input){
                    "Todas Las Opciones" ->productos = arrayOf("Pan Tipo Molde","pan Tipo Marraqueta","Pan Tipo Hallulla","Harinas","Jugos Con Azucar", "Bebidas Light", "Energeticas","Ramitas", "Galletas","Queques", "Alfajores","Papas Fritas","13")
                    "Pan y harinas" -> productos = arrayOf("Pan Tipo Molde","pan Tipo Marraqueta","Pan Tipo Hallulla","Harinas")
                    "Jugos y Bebidas" -> productos = arrayOf("Jugos Con Azucar", "Bebidas Light", "Energeticas")
                    "Snacks" -> productos = arrayOf("Ramitas", "Galletas","Queques", "Alfajores","Papas Fritas")
                    "Otros" -> productos = arrayOf("Condimentos","Avenas","Pastas","Frutas y Verduras")
                }
                arrayAdapterListView = ArrayAdapter(this@BuscadorProductos,android.R.layout.simple_list_item_1,productos)
                lv_datos.adapter = arrayAdapterListView
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }


    }
}