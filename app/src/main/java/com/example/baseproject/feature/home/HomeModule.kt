package com.example.baseproject.feature.home

import android.content.Context
import com.example.baseproject.feature.home.breed_detail.BreedDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
internal val homeModule = module {
    viewModel {
        HomeViewModel(
            getCatBreedsUseCase = get(),
            logoutUsernameUseCase = get(),
            resources = get<Context>().resources
        )
    }
    viewModel { (breedId: String) ->
        BreedDetailViewModel(breedId = breedId, getBreedByIdUseCase = get())
    }
}