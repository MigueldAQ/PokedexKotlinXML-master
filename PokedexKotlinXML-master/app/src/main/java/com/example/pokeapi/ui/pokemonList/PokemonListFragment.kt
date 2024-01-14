package com.example.pokeapi.ui.pokemonList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapi.R
import com.example.pokeapi.data.model.consumedModel.PokeListItem
import com.example.pokeapi.databinding.FragmentPokemonListBinding
import com.example.pokeapi.ui.pokemonFragment.PokemonDetailFragment
import com.example.pokeapi.ui.pokemonList.pokemonListRecView.PokemonListAdapter
import com.example.pokeapi.ui.viewModel.DataState
import com.example.pokeapi.ui.viewModel.PokemonListViewModel

class PokemonListFragment : Fragment() {

    private lateinit var binding: FragmentPokemonListBinding
    private val viewModel: PokemonListViewModel by viewModels()
    private val adapter: PokemonListAdapter = PokemonListAdapter(emptyList()) {pokemon: PokeListItem ->
        navigate(pokemon)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllPokemons()
        binding.btnTryAgain.setOnClickListener { viewModel.getAllPokemons() }
        recyclerViewConfig()

        viewModel.dataState.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Loading -> {

                    binding.btnTryAgain.isVisible = false

                }
                is DataState.Error -> {

                    binding.recyclerView.isVisible = false
                    binding.btnTryAgain.isVisible = true
                    binding.progressBar.isVisible = false

                    Log.i("Joaking","error")
                }
                is DataState.Success -> {

                    binding.recyclerView.isVisible = true
                    binding.btnTryAgain.isVisible = false
                    binding.progressBar.isVisible = false

                    // Actualiza la vista con la lista de Pokemon
                    adapter.updateList(dataState.pokemonList)
                    binding.editTextTextFilter.addTextChangedListener { userFilter ->
                        adapter.updateList(
                            viewModel.filteredPokemons(
                                userFilter.toString(),
                                dataState.pokemonList
                            )
                        )
                    }
                }

            }
        }


    }


    private fun recyclerViewConfig() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }
    //pokemon:PokeListItem


    private fun navigate(pokemon:PokeListItem) {

        val bundle = Bundle()
        bundle.putString("texto", pokemon.name)
        val pokemonDetailFragment = PokemonDetailFragment()
        pokemonDetailFragment.arguments = bundle
        findNavController().navigate(R.id.action_pokemonListFragment_to_pokemonDetailFragment, bundle)
    }

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }

}
