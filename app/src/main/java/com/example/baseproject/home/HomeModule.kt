package com.example.baseproject.home

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val homeModule = module {
    viewModel { HomeViewModel() }
}