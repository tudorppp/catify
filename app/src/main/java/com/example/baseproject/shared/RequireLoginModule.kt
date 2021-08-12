package com.example.baseproject.shared

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val requireLoginModule = module {
    viewModel { RequireLoginViewModel(authManager = get()) }
}