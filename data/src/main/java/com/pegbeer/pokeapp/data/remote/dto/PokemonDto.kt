package com.pegbeer.pokeapp.data.remote.dto

import com.pegbeer.pokeapp.domain.model.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>,
)