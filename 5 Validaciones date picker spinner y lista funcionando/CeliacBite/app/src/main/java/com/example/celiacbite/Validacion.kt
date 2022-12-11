package com.example.celiacbite

import android.util.Patterns
import java.util.regex.Pattern

class Validacion {
    // 1RA VALIDACION SI EL TEXTO ES NULO
    fun validacionNulo(texto:String):Boolean{
        return texto.trim().equals("") || texto.trim().length==0
    }
    fun validacioncamposIguales(texto:String, texto2:String):Boolean{
        return !texto.trim().equals(texto2.trim())
    }
    //2DA VALIDACION FORMATO DEL NOMBRE SEGUN UNA EXPRESION REGULAR.
    fun validarNombre(nombre:String):Boolean{
        val pattern = Pattern.compile("^[a-zA-Z]\$")
        return pattern.matcher(nombre).matches()
    }
    //3RA VALIDACION CORREO VALIDO
    fun validarCorreo(correo:String):Boolean{
        return !Patterns.EMAIL_ADDRESS.matcher(correo).matches()
    }
}