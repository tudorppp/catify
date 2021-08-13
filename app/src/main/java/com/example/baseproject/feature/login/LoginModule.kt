package com.example.baseproject.feature.login

import android.content.Context
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val loginModule = module {
    viewModel {
        LoginViewModel(
            loginWithUsernameUseCase = get(),
            resources = get<Context>().resources
        )
    }
}