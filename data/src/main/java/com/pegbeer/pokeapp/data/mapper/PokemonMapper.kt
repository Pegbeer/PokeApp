package com.pegbeer.pokeapp.data.mapper

import com.pegbeer.pokeapp.data.local.entity.PokemonEntity
import com.pegbeer.pokeapp.data.remote.dto.PokemonDto
import me.pegbeer.pokeapp.core.model.Pokemon

fun PokemonDto.toPokemonEntity():PokemonEntity{
    return PokemonEntity(
        page = this.page,
        name = this.name,
        url = this.url
    )
}

fun PokemonEntity.toPokemon():Pokemon{
    return Pokemon(
        page = this.page,
        name = this.name,
        url = this.url
    )
}

fun Pokemon.toEntity():PokemonEntity{
    return PokemonEntity(
        page = this.page,
        name = this.name,
        url = this.url
    )
}