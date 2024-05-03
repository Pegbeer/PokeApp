package com.pegbeer.pokeapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.pegbeer.pokeapp.data.local.PokemonDatabase
import com.pegbeer.pokeapp.data.local.entity.PokemonEntity
import com.pegbeer.pokeapp.data.mapper.toPokemonEntity
import com.pegbeer.pokeapp.data.remote.PokeAppService
import me.pegbeer.pokeapp.core.model.Pokemon

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val database: PokemonDatabase,
    private val api : PokeAppService
) : RemoteMediator<Int,Pokemon>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Pokemon>
    ): MediatorResult {

        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val response = api.fetchPokemonList(loadKey ?: 0, state.config.pageSize)
            if(!response.isSuccessful) return MediatorResult.Error(Exception(response.message()))


            val pokemons = response.body()!!.results.map { pokemon ->
                pokemon.toPokemonEntity()
            }

            database.dao.insertAllPokemon(pokemons)

            MediatorResult.Success(endOfPaginationReached = pokemons.isEmpty())
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }


}