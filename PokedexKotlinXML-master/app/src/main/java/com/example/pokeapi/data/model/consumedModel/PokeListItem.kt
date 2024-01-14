package com.example.pokeapi.data.model.consumedModel

import com.example.capitalizeFirstChar
import com.example.pokeapi.data.model.modelResponse.ResultPokemon

class PokeListItem(
    val id: Int,
    val name: String,
    val img: String
)

private const val URL_RAW = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

fun ResultPokemon.toDomain(): PokeListItem {
    val arrayUrl = url.split("/")
    val id = arrayUrl[arrayUrl.size - 2].toInt()
    val name = capitalizeFirstChar(name)
    val img = "$URL_RAW$id.png"
    return PokeListItem(id, name, img)
}