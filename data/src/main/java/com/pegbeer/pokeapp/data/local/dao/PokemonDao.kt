package com.pegbeer.pokeapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.pegbeer.pokeapp.data.local.entity.PokemonEntity

@Dao
interface PokemonDao {

    @Upsert
    suspend fun insertAllPokemon(data:List<PokemonEntity>)

    @Query("SELECT * FROM pokemonentity WHERE page = :page LIMIT :pageSize")
    suspend fun getPokemonList(page:Int, pageSize:Int = 20):List<PokemonEntity>

    @Query("SELECT * FROM pokemonentity WHERE :name = name")
    suspend fun getPokemonByName(name:String):PokemonEntity

    @Query("DELETE FROM pokemonentity")
    suspend fun deleteAll()
}