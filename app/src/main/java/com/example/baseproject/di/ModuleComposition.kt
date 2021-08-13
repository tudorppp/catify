package com.example.baseproject.di

import com.example.baseproject.feature.home.homeModule
import com.example.baseproject.feature.login.loginModule
import com.example.baseproject.shared.requireLoginModule
import com.example.baseproject.util.sharedPrefsModule
import org.koin.core.module.Module

internal fun createModules(): Set<Module> {
    return setOf(
        homeModule,
        loginModule,
        requireLoginModule,
        sharedPrefsModule,
    ) + coreModules()
}