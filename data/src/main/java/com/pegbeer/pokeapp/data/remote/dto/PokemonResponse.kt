package com.pegbeer.pokeapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonDto>,
)