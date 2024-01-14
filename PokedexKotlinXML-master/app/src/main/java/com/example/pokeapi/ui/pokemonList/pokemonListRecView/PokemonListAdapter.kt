package com.example.pokeapi.ui.pokemonList.pokemonListRecView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.R
import com.example.pokeapi.data.model.consumedModel.PokeListItem

class PokemonListAdapter(private var pokemonList: List<PokeListItem>,private val onItemClick: (pokemon:PokeListItem) -> Unit) :
    RecyclerView.Adapter<PokemonListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.pokemonlistitem, parent, false)
        return PokemonListViewHolder(view)


    }

    fun updateList(newPokemonList: List<PokeListItem>) {
        this.pokemonList = newPokemonList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bind(pokemon,onItemClick)
    }
}