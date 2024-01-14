package com.example.pokeapi.data.network

import com.example.pokeapi.data.model.modelResponse.PokeModelDetails
import com.example.pokeapi.data.model.modelResponse.PokemonListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {

    @GET(value = "pokemon?limit=1154")
    suspend fun getListPokemon(): Response<PokemonListModel>

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetails(@Path("pokemonName") pokemonName: String): PokeModelDetails

}