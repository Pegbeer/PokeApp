package com.pegbeer.pokeapp.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.paging.util.getClippedRefreshKey
import androidx.room.withTransaction
import com.pegbeer.pokeapp.data.local.PokemonDatabase
import com.pegbeer.pokeapp.data.mapper.toPokemonEntity
import com.pegbeer.pokeapp.data.remote.PokeAppService
import me.pegbeer.pokeapp.core.model.Pokemon
import kotlin.math.roundToInt

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val database: PokemonDatabase,
    private val api : PokeAppService
) : RemoteMediator<Int,Pokemon>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Pokemon>
    ): MediatorResult {
        Log.d(TAG, "loadType: $loadType")
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull()?.getNumber() ?: 0
                }
            }


            val response = api.fetchPokemonList(state.config.pageSize, loadKey)
            if(!response.isSuccessful) return MediatorResult.Error(Exception(response.message()))

            val pageResult = (loadKey / state.config.pageSize) + 1

            val pokemons = response.body()!!.results.map { pokemon ->
                pokemon.toPokemonEntity().apply {
                    page = pageResult
                }
            }

            database.withTransaction {
                if(loadType == LoadType.REFRESH){
                    database.dao.deleteAll()
                }
                database.dao.insertAllPokemon(pokemons)
            }

            Log.d(TAG, "endOfPaginationReached: ${pokemons.isEmpty()}")

            MediatorResult.Success(endOfPaginationReached = pokemons.isEmpty())
        } catch (exception: Exception) {
            Log.d(TAG, "load: $exception")
            MediatorResult.Error(exception)
        }
    }

    companion object{
        const val TAG = "PokemonRemoteMediator"
    }

}