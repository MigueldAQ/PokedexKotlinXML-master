package com.example.pokeapi.data.network

import com.example.pokeapi.core.RetrofitHelper
import com.example.pokeapi.data.model.modelResponse.PokeModelDetails
import com.example.pokeapi.data.model.modelResponse.ResultPokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonListService {

    suspend fun getPokemons(): List<ResultPokemon> {
        return withContext(Dispatchers.IO) {
            val response = RetrofitHelper.getRetrofit().create(PokemonAPI::class.java).getListPokemon()
            response.body()?.results ?: emptyList()
        }
    }

    suspend fun getPokemonDetail(pokemonName: String): PokeModelDetails {

        return withContext(Dispatchers.IO) {
            val pokemonDetail = RetrofitHelper.getRetrofit().create(PokemonAPI::class.java)
                .getPokemonDetails(pokemonName)
            pokemonDetail
        }

    }


}
