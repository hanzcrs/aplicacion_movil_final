package roomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import roomDatabase.entity.Usuario

@Dao
interface DaoUsuario {
    @Query("SELECT * FROM Usuario")
    suspend fun obtenerUsuarios():List<Usuario>

    @Query("SELECT * FROM Usuario WHERE mail=:mail")
    suspend fun obtenerUsuario(mail: String): List<Usuario>

    @Query("SELECT * FROM Usuario WHERE mail=:mail AND contrasena=:contrasena")
    suspend fun login(mail: String, contrasena: String): List<Usuario>

    @Insert
    suspend fun agregarUsuario(usuario: Usuario):Long

    // esto nos permite parametrizar
    @Query("UPDATE Usuario SET nombre=:nombre,contrasena=:contrasena WHERE mail=:mail")
    suspend fun actualizarUsuario(nombre:String, mail: String,contrasena:String): Int

    @Query("DELETE FROM Usuario WHERE mail=:mail")
    suspend fun borrarUsuario(mail: String)

}