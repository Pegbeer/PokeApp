package me.pegbeer.pokeapp.core.model


import android.util.Log
import kotlinx.serialization.Serializable


@Serializable
data class Pokemon(
    var page: Int,
    val name: String,
    val url: String,
) {

    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                "pokemon/other/official-artwork/$index.png"
    }

    fun capitalizedName(): String = name.replaceFirstChar { it.uppercase() }

    fun getNumberAsString():String{
        return try {
            val number = getIdFromUrl()
            "#$number"
        }catch (ex:Exception){
            Log.d(TAG, "getNumberAsString: $ex")
            "#???"
        }
    }

    fun getNumber():Int{
        return getIdFromUrl() ?: 0
    }

    private fun getIdFromUrl():Int?{
        val regex = Regex("/(\\d+)/\$")
        val matchResult = regex.find(url)
        return matchResult?.groupValues?.get(1)?.toInt()
    }

    companion object{
        const val TAG = "Pokemon"
    }
}