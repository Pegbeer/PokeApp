package me.pegbeer.pokeapp.core.repository


import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.pegbeer.pokeapp.core.model.Pokemon

interface Repository {
    suspend fun fetchPokemonList(page:Int): Flow<PagingData<Pokemon>>
}