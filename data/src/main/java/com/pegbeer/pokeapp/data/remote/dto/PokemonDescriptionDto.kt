package com.pegbeer.pokeapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDescriptionDto(
    val description:String
)