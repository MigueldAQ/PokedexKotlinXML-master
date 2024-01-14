package com.example.pokeapi.ui.pokemonList.pokemonListRecView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.data.model.consumedModel.PokeListItem
import com.example.pokeapi.databinding.PokemonlistitemBinding
import com.squareup.picasso.Picasso

class PokemonListViewHolder(view:View,) : RecyclerView.ViewHolder(view)  {
    private val binding = PokemonlistitemBinding.bind(view)

    fun bind(pokemon:PokeListItem,onItemClick: (pokemon:PokeListItem) -> Unit){
        Picasso.get().load(pokemon.img).into(binding.imviPokemonPhoto)
        binding.tvPokemonName.text = pokemon.name
        itemView.setOnClickListener { onItemClick(pokemon) }
    }

}