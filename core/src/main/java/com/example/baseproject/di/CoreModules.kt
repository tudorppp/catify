package com.example.baseproject.di

import com.example.baseproject.networking.restModule
import com.example.baseproject.repository.authentication.authDataSourceModule
import com.example.baseproject.repository.cats.catsModule
import com.example.baseproject.usecase.useCaseModule
import org.koin.core.module.Module

fun coreModules(): Set<Module> {
    return setOf(restModule, authDataSourceModule, useCaseModule, catsModule)
}