package com.pegbeer.pokeapp.data.remote

import com.pegbeer.pokeapp.data.remote.dto.PokemonDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface PokeAppService {


    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit")limit:Int = 20,
        @Query("offset")offset:Int = 0
    ):Response<PokemonDto>
}