package me.pegbeer.pokeapp.core

sealed class Result<out T>{
    class Loading<T> : Result<T>()
    data class Success<T>(val data:T) : Result<T>()
    data class Error<T>(val message:String?, val code:Int?):Result<T>()
}