package com.pegbeer.pokeapp.ui.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import me.pegbeer.pokeapp.core.repository.Repository

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModel(
    private val repository: Repository,

) : ViewModel() {

    private val id = MutableStateFlow(0)

    val pokemonDetail = id.flatMapLatest {
        repository.fetchPokemonDetail(it)
    }

    fun setId(id:Int){
        this.id.value = id
    }

}