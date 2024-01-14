package com.example.pokeapi.domain

import com.example.pokeapi.data.PokemonListRepository
import com.example.pokeapi.data.model.consumedModel.PokeListItem

class GetPokemonListUseCase {

    private val repository = PokemonListRepository()

    suspend operator fun invoke(): List<PokeListItem>{
        return repository.getAllPokemons()
    }

}