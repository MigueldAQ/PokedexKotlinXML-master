package com.example.pokeapi.domain

import com.example.pokeapi.data.PokemonListRepository
import com.example.pokeapi.data.model.consumedModel.PokeItemDetails

class GetPokemonDetailUseCase {
    private val repository = PokemonListRepository()

    suspend operator fun invoke(pokemonName: String): PokeItemDetails {
        return repository.getPokemonDetail(pokemonName)
    }
}