package me.pegbeer.pokeapp.core.repository


import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.pegbeer.pokeapp.core.model.Pokemon

interface Repository {
    fun fetchPokemonList(): Flow<PagingData<Pokemon>>
}