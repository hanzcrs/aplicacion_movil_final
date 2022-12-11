package roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import roomDatabase.dao.DaoRecetas
import roomDatabase.dao.DaoUsuario
import roomDatabase.entity.Recetas
import roomDatabase.entity.Usuario

// se borra la clase gerada por defecto ya que esta clase serà abstracta.

// agregar aca de igual forma
@Database(
    entities = [Usuario::class, Recetas::class],
    version = 1
)

// siempre se debe definir la funcion abstracta en esta parte siempre cuando se mantenga Dao y entidad
abstract class Db:RoomDatabase(){
    abstract fun daoUsuario():DaoUsuario
    abstract fun daoRecetas():DaoRecetas
}