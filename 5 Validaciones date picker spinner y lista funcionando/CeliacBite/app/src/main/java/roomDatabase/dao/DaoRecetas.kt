package roomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import roomDatabase.entity.Recetas


@Dao
interface DaoRecetas {
    @Query("SELECT * FROM Recetas")
    suspend fun obtenerReceta():List<Recetas>

    @Query("SELECT * FROM Recetas WHERE user=:user")
    suspend fun obtenerRecetasUsuario(user: String): List<Recetas>

    @Query("SELECT * FROM Recetas WHERE nombre_receta=:nombre_receta AND user=:user")
    suspend fun obtenerRecetasNombre(nombre_receta: String, user: String): List<Recetas>

    @Insert
    suspend fun agregarReceta(Recetas: Recetas):Long

    @Query("UPDATE Recetas SET nombre_receta=:nombre_receta,tipo_alimento=:tipo_alimento,instrucciones=:instrucciones,ingredientes=:ingredientes WHERE id=:id")
    suspend fun actualizarReceta(nombre_receta:String, tipo_alimento:String, instrucciones: String,ingredientes:String, id:Long): Int

    @Query("DELETE FROM Recetas WHERE id=:id")
    suspend fun borrarReceta(id: Long)
}