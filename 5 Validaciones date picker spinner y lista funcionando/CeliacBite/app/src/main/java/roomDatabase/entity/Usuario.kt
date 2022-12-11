package roomDatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Usuario {

    @PrimaryKey
    var mail:String
    var nombre:String? = null
    var contrasena: String? = null
    var fecha_nacimiento: String? = null

    constructor(
        mail: String,
        nombre: String?,
        contrasena: String?,
        fecha_nacimiento: String?
    ) {
        this.mail = mail
        this.nombre = nombre
        this.contrasena = contrasena
        this.fecha_nacimiento = fecha_nacimiento
    }

    override fun toString(): String {
        return "Usuario(mail='$mail', nombre=$nombre, contrasena=$contrasena, fecha_nacimiento=$fecha_nacimiento)"
    }

}