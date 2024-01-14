package com.example.pokeapi.ui.pokemonFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pokeapi.data.model.consumedModel.PokeItemDetails
import com.example.pokeapi.databinding.FragmentPokemonDetailBinding
import com.example.pokeapi.ui.viewModel.PokemonDetailViewModel
import com.example.pokeapi.ui.viewModel.PokemonState
import com.squareup.picasso.Picasso

class PokemonDetailFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding
    private val viewModel: PokemonDetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val texto: String = (requireArguments().getString("texto")).toString()
        viewModel.getPokemonDetail(texto.lowercase())
        binding.onBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnTryAgain.setOnClickListener {
            viewModel.getPokemonDetail(texto.lowercase())
        }

        viewModel.pokemonState.observe(viewLifecycleOwner) { pokemonState ->
            when (pokemonState) {
                is PokemonState.Loading -> {
                    binding.progressBarDetail.isVisible = true
                    binding.btnTryAgain.isVisible = false


                }
                is PokemonState.Error -> {

                    binding.btnTryAgain.isVisible = true
                    binding.progressBarDetail.isVisible = false

                }
                is PokemonState.Success -> {
                    binding.btnTryAgain.isVisible = false
                    binding.progressBarDetail.isVisible = false

                    Picasso.get().load(pokemonState.pokemon.img).into(binding.imviPokemon)
                    binding.tvPokemon.text = pokemonState.pokemon.name
                    setStatsOnLayout(pokemonState.pokemon)

                    viewModel.setTypes1(
                        pokemon = pokemonState.pokemon,
                        tvType1 = binding.tvType1,
                        tvType2 = binding.tvType2,
                        context = requireContext()
                    )

                    binding.linearLayout3.isVisible = true
                    binding.linearLayout.isVisible = true


                }

            }
        }


    }

    //pokemon:PokeListItem


    companion object {
        fun newInstance() = PokemonDetailFragment()
    }

    private fun setStatsOnLayout(pokemon: PokeItemDetails) {
        viewModel.statsOnUi(view = binding.viewHP, stat = pokemon.hp)
        viewModel.statsOnUi(view = binding.viewDef, stat = pokemon.defense)
        viewModel.statsOnUi(view = binding.viewSpeed, stat = pokemon.speed)
        viewModel.statsOnUi(view = binding.viewAttk, stat = pokemon.attack)
        viewModel.statsOnUi(view = binding.viewSpAttk, stat = pokemon.specialAttack)
        viewModel.statsOnUi(view = binding.viewSpDef, stat = pokemon.specialDefense)
    }


}