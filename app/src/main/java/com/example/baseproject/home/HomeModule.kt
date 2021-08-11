package com.example.baseproject.home

import com.example.baseproject.home.breed_detail.BreedDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val homeModule = module {
    viewModel { HomeViewModel(getCatBreedsUseCase = get()) }
    viewModel { (breedId: String) -> BreedDetailViewModel(breedId = breedId, getBreedByIdUseCase = get()) }
}