package com.pegbeer.pokeapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pegbeer.pokeapp.data.local.PokemonDatabase
import com.pegbeer.pokeapp.data.mapper.toPokemon
import me.pegbeer.pokeapp.core.model.Pokemon

class PokemonPagingSource (
    private val database: PokemonDatabase
): PagingSource<Int,Pokemon>() {
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val nextPageNumber = params.key ?: 0
            val data = database.dao.getPokemonList(nextPageNumber)
            LoadResult.Page(
                data = data.map { it.toPokemon() },
                prevKey = if (nextPageNumber == 0) null else nextPageNumber - 1,
                nextKey = if (data.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}