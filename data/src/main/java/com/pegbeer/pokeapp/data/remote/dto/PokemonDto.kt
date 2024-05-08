package com.pegbeer.pokeapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    var page: Int = 0,
    val name: String,
    val url: String,
){

    fun getNumber():Int{
        return url.split("/".toRegex()).dropLast(1).last().toInt()
    }
}