package com.pegbeer.pokeapp.data.mapper

import com.pegbeer.pokeapp.data.local.entity.PokemonEntity
import com.pegbeer.pokeapp.data.remote.dto.PokemonDto
import com.pegbeer.pokeapp.data.remote.dto.PokemonInfoDto
import me.pegbeer.pokeapp.core.model.Pokemon
import me.pegbeer.pokeapp.core.model.PokemonDetail

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

fun PokemonInfoDto.toPokemonDetail():PokemonDetail{
    return PokemonDetail(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight,
        experience = this.experience,
        types = this.types.map { it.type.name }
    )
}