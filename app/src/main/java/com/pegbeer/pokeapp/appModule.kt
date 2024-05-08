package com.pegbeer.pokeapp

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.pegbeer.pokeapp.ui.home.HomeViewModel

val appModule = module {
    viewModelOf(::HomeViewModel)
}