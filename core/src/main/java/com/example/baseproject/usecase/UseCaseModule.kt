package com.example.baseproject.usecase

import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val useCaseModule = module {
    factory { LoginWithUsernameUseCase(loginRepository = get(named("usernameLoginRepository"))) }
}