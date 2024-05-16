package me.pegbeer.pokeapp.core.model

import kotlin.random.Random


data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<String>,
    val description:String,
    val hp: Int = Random.nextInt(MAX_VALUE),
    val attack: Int = Random.nextInt(MAX_VALUE),
    val defense: Int = Random.nextInt(MAX_VALUE),
    val specialAttack:Int = Random.nextInt(MAX_VALUE),
    val specialDefense:Int = Random.nextInt(MAX_VALUE),
    val speed: Int = Random.nextInt(MAX_VALUE),
    val exp: Int = Random.nextInt(MAX_VALUE),
) {

    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f kg", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f m", height.toFloat() / 10)
    fun getHpString(): String = " $hp/$MAX_VALUE"
    fun getAttackString(): String = " $attack/$MAX_VALUE"
    fun getDefenseString(): String = " $defense/$MAX_VALUE"
    fun getSpeedString(): String = " $speed/$MAX_VALUE"
    fun getExpString(): String = " $exp/$MAX_VALUE"

    fun capitalizedName(): String = name.replaceFirstChar { it.uppercase() }

    companion object {
        const val MAX_VALUE = 300
    }
}