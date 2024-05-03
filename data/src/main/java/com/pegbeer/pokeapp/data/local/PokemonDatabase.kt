package com.pegbeer.pokeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pegbeer.pokeapp.data.local.dao.PokemonDao
import com.pegbeer.pokeapp.data.local.entity.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    exportSchema = false,
    version = 1
)
abstract class PokemonDatabase : RoomDatabase(){
    abstract val dao:PokemonDao
}