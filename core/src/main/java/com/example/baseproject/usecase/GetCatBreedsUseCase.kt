package com.example.baseproject.usecase

import androidx.paging.PagingData
import com.example.baseproject.model.entity.Breed
import com.example.baseproject.repository.breed.CatBreedRepository
import io.reactivex.rxjava3.core.Flowable

class GetCatBreedsUseCase internal constructor(private val catBreedRepository: CatBreedRepository) {

    operator fun invoke(): Flowable<PagingData<Breed>> {
        return catBreedRepository.getBreeds()
    }
}