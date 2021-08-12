package com.example.baseproject.usecase

import com.example.baseproject.repository.authentication.USERNAME_LOGIN_REPOSITORY
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val useCaseModule = module {
    factory { LoginWithUsernameUseCase(loginRepository = get(named(USERNAME_LOGIN_REPOSITORY))) }
    factory { GetCatBreedsUseCase(catBreedRepository = get()) }
    factory { GetBreedByIdUseCase(catBreedRepository = get()) }
    factory { LogoutUsernameUseCase(loginRepository = get(named(USERNAME_LOGIN_REPOSITORY))) }
}