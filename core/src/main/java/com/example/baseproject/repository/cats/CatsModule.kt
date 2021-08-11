package com.example.baseproject.repository.cats

import com.example.baseproject.networking.service.CatsService
import org.koin.dsl.module
import retrofit2.Retrofit

val catsModule = module {

    single { get<Retrofit>().create(CatsService::class.java) }

    factory<CatBreedRepository> { CatBreedRepositoryImpl(catsService = get()) }
}