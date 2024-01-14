package com.example.pokeapi.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capitalizeFirstChar
import com.example.pokeapi.data.model.consumedModel.PokeListItem
import com.example.pokeapi.domain.GetPokemonListUseCase
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DataState {
    object Loading : DataState()
    object Error : DataState()
    data class Success(val pokemonList: List<PokeListItem>) : DataState()
}


class PokemonListViewModel : ViewModel() {
    private val _dataState = MutableLiveData<DataState>()
    val dataState: LiveData<DataState> = _dataState

    fun getAllPokemons() {
        viewModelScope.launch {
            _dataState.postValue(DataState.Loading)
            Log.e("PokemonListViewModel", "Cargando ando")
            try {
                val response = GetPokemonListUseCase().invoke()
                _dataState.postValue(DataState.Success(response))
                Log.e("Joaking", "$_dataState")

            } catch (e: IOException) {
                _dataState.postValue(DataState.Error)
                Log.e("PokemonListViewModel", "Error al obtener la lista de pokemons", e)
            }
        }
    }

    fun filteredPokemons(
        pokemonNameFilter: String,
        pokemonList: List<PokeListItem>
    ): List<PokeListItem> {

        val filteredPokemon = pokemonList.filter { pokemon ->
            pokemon.name.contains(pokemonNameFilter.lowercase()) || pokemon.name.contains(
                capitalizeFirstChar(pokemonNameFilter)
            )
        }

        return filteredPokemon
    }


}