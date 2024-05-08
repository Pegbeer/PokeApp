package com.pegbeer.pokeapp.data.paging


import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pegbeer.pokeapp.data.local.PokemonDatabase
import com.pegbeer.pokeapp.data.mapper.toPokemon
import com.pegbeer.pokeapp.data.mapper.toPokemonEntity
import com.pegbeer.pokeapp.data.remote.PokeAppService
import com.pegbeer.pokeapp.data.remote.repository.RepositoryImpl
import me.pegbeer.pokeapp.core.model.Pokemon

class PokemonPagingSource (
    private val database: PokemonDatabase,
    private val api: PokeAppService
): PagingSource<Int,Pokemon>() {
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val page = params.key ?: STARTING_KEY

            val loadKey = when(page){
                0 -> {
                    database.dao.deleteAll()
                    page
                }
                else -> {
                    val lastItem = database.dao.getLastPokemon()?.toPokemon()
                    lastItem?.getNumber() ?: 0
                }
            }

            Log.i(TAG, "loadKey: $loadKey")

            val response = api.fetchPokemonList(offset = loadKey )
            if (!response.isSuccessful) throw RuntimeException(response.message())

            if(response.body()?.results.isNullOrEmpty()) throw NullPointerException("No more resources to load")

            val pageResult = (loadKey / RepositoryImpl.PAGING_SIZE) + 1

            Log.w(TAG, "load: $pageResult")

            database.dao.insertAllPokemon(
                response.body()!!.results.map {
                    it.apply {
                        it.page = pageResult
                    }.toPokemonEntity()
                }
            )

            LoadResult.Page(
                data = database.dao.getPokemonList(pageResult).map { it.toPokemon() },
                prevKey = if(page == 0) null else page - 1,
                nextKey = if (response.body()?.results.isNullOrEmpty()) null else page + 1
            )

        } catch (e: Exception) {
            Log.d(TAG, "load: $e")
            LoadResult.Error(e)
        }
    }

    companion object{
        const val TAG = "PokemonPagingSource"
        const val STARTING_KEY = 0
    }
}