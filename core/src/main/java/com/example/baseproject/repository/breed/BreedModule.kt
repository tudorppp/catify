package com.example.baseproject.repository.breed

import com.example.baseproject.networking.service.CatsService
import org.koin.dsl.module
import retrofit2.Retrofit

val catsModule = module {

    single { get<Retrofit>().create(CatsService::class.java) }

    single<CatBreedLocalSource> { CatBreedLocalSourceImpl() }

    factory<CatBreedRepository> { CatBreedRepositoryImpl(catsService = get(), catBreedLocalSource = get()) }
}