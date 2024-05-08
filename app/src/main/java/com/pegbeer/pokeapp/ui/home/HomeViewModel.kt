package com.pegbeer.pokeapp.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import me.pegbeer.pokeapp.core.repository.Repository


class HomeViewModel(
    repository: Repository
) : ViewModel() {

    val pokemons = repository.fetchPokemonList()
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)
}