package com.pegbeer.pokeapp.data.remote.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.pegbeer.pokeapp.data.local.PokemonDatabase
import com.pegbeer.pokeapp.data.mapper.toPokemon
import com.pegbeer.pokeapp.data.mapper.toPokemonDetail
import com.pegbeer.pokeapp.data.paging.PokemonPagingSource
import com.pegbeer.pokeapp.data.paging.PokemonRemoteMediator
import com.pegbeer.pokeapp.data.remote.PokeAppService
import com.pegbeer.pokeapp.data.remote.dto.PokemonResponse
import me.pegbeer.pokeapp.core.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import me.pegbeer.pokeapp.core.Result
import me.pegbeer.pokeapp.core.model.Pokemon
import me.pegbeer.pokeapp.core.model.PokemonDetail

internal class RepositoryImpl(
    private val service: PokeAppService,
    private val database:PokemonDatabase
) : Repository {


    override fun fetchPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(PAGING_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { PokemonPagingSource(database,service) }
        ).flow
    }

    override fun fetchPokemonDetail(id:Int): Flow<Result<PokemonDetail>> = flow {
        val response = service.fetchPokemonDetail(id)
        if(!response.isSuccessful) emit(Result.Error(response.message(),response.code()))

        val descriptionResponse = service.fetchPokemonDescription(id)
        if(!descriptionResponse.isSuccessful) emit(Result.Error(descriptionResponse.message(),descriptionResponse.code()))


        response.body()?.let {
            emit(Result.Success(it.toPokemonDetail(descriptionResponse.body()?.description)))
        }
    }

    companion object{
        const val PAGING_SIZE = 20
    }
}