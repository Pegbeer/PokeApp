package me.pegbeer.pokeapp.core.model


data class Pokemon(
    var page: Int = 0,
    val name: String,
    val url: String,
) {

    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                "pokemon/other/official-artwork/$index.png"
    }
    fun capitalizedName(): String = name.replaceFirstChar { it.uppercase() }

    fun getNumber():String{
        val number = url.split("/".toRegex()).dropLast(1).last()
        return "#$number"
    }
}