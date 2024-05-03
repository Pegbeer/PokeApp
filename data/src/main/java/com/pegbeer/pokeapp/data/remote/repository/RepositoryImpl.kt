package com.pegbeer.pokeapp.data.remote.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pegbeer.pokeapp.data.local.PokemonDatabase
import com.pegbeer.pokeapp.data.paging.PokemonPagingSource
import com.pegbeer.pokeapp.data.paging.PokemonRemoteMediator
import com.pegbeer.pokeapp.data.remote.PokeAppService
import com.pegbeer.pokeapp.data.remote.dto.PokemonResponse
import me.pegbeer.pokeapp.core.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.pegbeer.pokeapp.core.Result
import me.pegbeer.pokeapp.core.model.Pokemon

class RepositoryImpl(
    private val service: PokeAppService,
    private val database:PokemonDatabase
) : Repository {


    @OptIn(ExperimentalPagingApi::class)
    override suspend fun fetchPokemonList(page:Int): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(PAGING_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { PokemonPagingSource(database) },
            remoteMediator = PokemonRemoteMediator(database,service)
        ).flow
    }

    companion object{
        private const val PAGING_SIZE = 20
    }
}