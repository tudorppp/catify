package com.example.baseproject.di

import com.example.baseproject.networking.RestModule
import com.example.baseproject.repository.authentication.AuthDataSourcesModule
import com.example.baseproject.repository.authentication.AuthManager
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RestModule::class, AuthDataSourcesModule::class])
interface CoreComponent {

    fun authManager(): AuthManager

}