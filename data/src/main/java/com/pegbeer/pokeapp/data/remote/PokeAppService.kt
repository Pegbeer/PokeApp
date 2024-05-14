package com.pegbeer.pokeapp.data.remote

import com.pegbeer.pokeapp.data.remote.dto.PokemonInfoDto
import com.pegbeer.pokeapp.data.remote.dto.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokeAppService {


    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit")limit:Int = 20,
        @Query("offset")offset:Int? = 0
    ):Response<PokemonResponse>

    @GET("pokemon/{id}")
    suspend fun fetchPokemonDetail(
        @Path("id") id:Int
    ):Response<PokemonInfoDto>

    companion object{
        const val BASEURL = "https://pokeapi.co/api/v2/"
    }
}