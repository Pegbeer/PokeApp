package com.pegbeer.pokeapp.data.remote.dto

data class PokemonDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    //val results: List<Pokemon>,
)
