package com.example.pokeapi.data

import com.example.pokeapi.data.model.consumedModel.PokeItemDetails
import com.example.pokeapi.data.model.consumedModel.PokeListItem
import com.example.pokeapi.data.model.consumedModel.toDomain
import com.example.pokeapi.data.network.PokemonListService



class PokemonListRepository {
    private val api = PokemonListService()

    suspend fun getAllPokemons(): List<PokeListItem> {
        val response = api.getPokemons()
        return response.map { it.toDomain() }
    }

    suspend fun getPokemonDetail(pokemonName:String): PokeItemDetails {
        val response = api.getPokemonDetail(pokemonName)
        return response.toDomain()

    }
}