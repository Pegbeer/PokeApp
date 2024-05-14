package me.pegbeer.pokeapp.core.util

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import kotlin.reflect.KClass

fun <T : java.io.Serializable> Bundle.serializable(key:String, clazz: Class<T>):T?{
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
        getSerializable(key,clazz)
    }else{
        getSerializable(key) as? T
    }
}

fun <T : Parcelable> Bundle.parcelable(key: String,clazz: Class<T>):T?{
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
        getParcelable(key,clazz)
    }else{
        getParcelable(key) as? T
    }
}