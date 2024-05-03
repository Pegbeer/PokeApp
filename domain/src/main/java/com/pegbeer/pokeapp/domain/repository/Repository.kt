package com.pegbeer.pokeapp.domain.repository

import com.pegbeer.pokeapp.data.remote.dto.PokemonDto
import kotlinx.coroutines.flow.Flow
import me.pegbeer.pokeapp.core.Result
import retrofit2.Response

interface Repository {
    suspend fun fetchPokemonList(page:Int): Flow<Result<PokemonDto>>
}