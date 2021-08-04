package com.example.baseproject.di

import android.content.Context
import com.example.baseproject.Application
import com.example.baseproject.home.HomeModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        HomeModule::class
    ],
    dependencies = [CoreComponent::class]
)
interface ApplicationComponent : AndroidInjector<Application> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}