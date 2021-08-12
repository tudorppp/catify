package com.example.baseproject.home

import com.example.baseproject.home.breed_detail.BreedDetailViewModel
import com.example.baseproject.shared.RequireLoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val homeModule = module {
    viewModel { HomeViewModel(getCatBreedsUseCase = get(),logoutUsernameUseCase = get()) }
    viewModel { (breedId: String) -> BreedDetailViewModel(breedId = breedId, getBreedByIdUseCase = get()) }
}