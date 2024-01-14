package com.example

import java.util.*

fun capitalizeFirstChar(str:String):String{
    return str.replaceFirstChar {
        // cada nombre con Mayuscula
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}