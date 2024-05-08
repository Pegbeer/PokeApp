package com.pegbeer.pokeapp

import android.app.Application
import com.pegbeer.pokeapp.data.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class PokeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@PokeApp)
            modules(appModule)
            modules(dataModule)
        }
    }
}