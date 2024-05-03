package com.pegbeer.pokeapp.data.remote.repository

import com.pegbeer.pokeapp.data.remote.PokeAppService
import com.pegbeer.pokeapp.data.remote.dto.PokemonDto
import com.pegbeer.pokeapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.pegbeer.pokeapp.core.Result
import retrofit2.Response

class RepositoryImpl(
    private val service: PokeAppService
) : Repository {
    override suspend fun fetchPokemonList(page:Int):Flow<Result<PokemonDto>> = flow{
        val response = service.fetchPokemonList(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE
        )

        if (!response.isSuccessful) emit(Result.Error(response.message(),response.code()))

        if (response.body() == null) emit(Result.Error(response.message(),response.code()))

        emit(Result.Success(response.body()!!))
    }

    companion object{
        private const val PAGING_SIZE = 20
    }
}