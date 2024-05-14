package com.pegbeer.pokeapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonInfoDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<TypeResponse>
){
    @Serializable
    data class TypeResponse(
        val slot: Int,
        val type: Type,
    )


    @Serializable
    data class Type(
        val name: String,
    )
}
