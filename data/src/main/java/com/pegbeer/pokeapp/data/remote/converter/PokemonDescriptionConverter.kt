package com.pegbeer.pokeapp.data.remote.converter

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.pegbeer.pokeapp.data.remote.dto.PokemonDescriptionDto
import java.lang.reflect.Type

class PokemonDescriptionConverter : JsonDeserializer<PokemonDescriptionDto> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PokemonDescriptionDto {
        val jsonObject = json?.asJsonObject
        val flavoursEntriesJsonArray = jsonObject?.get("flavor_text_entries")?.asJsonArray
        flavoursEntriesJsonArray?.forEach{
            val flavorJsonObject = it.asJsonObject
            val language = flavorJsonObject.get("language").asJsonObject
            val languageNameFromJson = language.get("name").asString
            if(languageNameFromJson == LANGUAGE_NAME){
                val flavorText = flavorJsonObject.get("flavor_text").asString.replace("\n"," ")
                return PokemonDescriptionDto(flavorText)
            }
        }
        return PokemonDescriptionDto("")
    }

    companion object{
        const val LANGUAGE_NAME = "es"
        const val TAG = "PokemonDescriptionConverter"
    }

}