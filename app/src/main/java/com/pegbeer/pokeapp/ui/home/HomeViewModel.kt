package com.pegbeer.pokeapp.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.lastOrNull
import me.pegbeer.pokeapp.core.Result
import me.pegbeer.pokeapp.core.model.Pokemon
import me.pegbeer.pokeapp.core.model.PokemonDetail
import me.pegbeer.pokeapp.core.repository.Repository


class HomeViewModel(
    repository: Repository
) : ViewModel() {

    val pokemons = repository.fetchPokemonList()
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)

    private val _pokemonSelected = MutableStateFlow<Pokemon?>(null)
    val pokemonSelected = _pokemonSelected.asStateFlow()

    val pokemonDetail: Flow<Result<PokemonDetail>> = _pokemonSelected.flatMapLatest {
        if(it != null){
            repository.fetchPokemonDetail(it.getNumber())
        }else{
            flowOf(Result.Loading())
        }
    }

    fun savePokemonSelected(pokemon: Pokemon?){
        _pokemonSelected.value = pokemon
    }

}