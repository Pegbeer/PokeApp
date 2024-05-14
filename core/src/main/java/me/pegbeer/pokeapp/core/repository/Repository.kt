package me.pegbeer.pokeapp.core.repository


import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.pegbeer.pokeapp.core.Result
import me.pegbeer.pokeapp.core.model.Pokemon
import me.pegbeer.pokeapp.core.model.PokemonDetail

interface Repository {
    fun fetchPokemonList(): Flow<PagingData<Pokemon>>
    fun fetchPokemonDetail(id:Int):Flow<Result<PokemonDetail>>
}