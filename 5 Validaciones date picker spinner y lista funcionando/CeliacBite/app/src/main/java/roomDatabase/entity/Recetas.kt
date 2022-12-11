package roomDatabase.entity

import android.app.DatePickerDialog
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Recetas {
    @PrimaryKey(autoGenerate = true)
    var id:Long=0
    var nombre_receta: String? = null
    var tipo_alimento: String? = null
    var ingredientes: String? = null
    var instrucciones: String? = null
    var user: String? = null


    constructor(
        nombre_receta: String,
        tipo_alimento: String?,
        ingredientes: String?,
        instrucciones: String?,
        user: String?
    ) {
        this.nombre_receta = nombre_receta
        this.tipo_alimento = tipo_alimento
        this.ingredientes = ingredientes
        this.instrucciones = instrucciones
        this.user = user
    }

    override fun toString(): String {
        return "Recetas(id=$id, nombre_receta=$nombre_receta, tipo_alimento=$tipo_alimento,ingredientes=$ingredientes,instrucciones=$instrucciones,user=$user)"
    }

}