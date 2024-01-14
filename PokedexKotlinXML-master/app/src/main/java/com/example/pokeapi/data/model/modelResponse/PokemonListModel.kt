package com.example.pokeapi.data.model.modelResponse

data class PokemonListModel(
    val results: List<ResultPokemon>
)

data class ResultPokemon(
    val name: String,
    val url: String
)