package com.pegbeer.pokeapp.data

import androidx.room.Room
import com.google.gson.Gson
import com.pegbeer.pokeapp.data.local.PokemonDatabase
import com.pegbeer.pokeapp.data.remote.PokeAppService
import com.pegbeer.pokeapp.data.remote.converter.PokemonDescriptionConverter
import com.pegbeer.pokeapp.data.remote.dto.PokemonDescriptionDto
import com.pegbeer.pokeapp.data.remote.interceptor.RequestInterceptor
import com.pegbeer.pokeapp.data.remote.repository.RepositoryImpl
import me.pegbeer.pokeapp.core.repository.Repository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    single {
        Gson().newBuilder()
            .registerTypeAdapter(PokemonDescriptionDto::class.java,PokemonDescriptionConverter())
            .create()
    }

    single {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
            .writeTimeout(60,TimeUnit.SECONDS)
            .addInterceptor(RequestInterceptor())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(PokeAppService.BASEURL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
            .create(PokeAppService::class.java)
    }

    single { Room.databaseBuilder(
        get(),
        PokemonDatabase::class.java,
        "pokeapp.db"
    ).build()
    }

    single<Repository> { RepositoryImpl(get(),get()) }
}