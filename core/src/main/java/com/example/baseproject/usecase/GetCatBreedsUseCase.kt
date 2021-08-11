package com.example.baseproject.usecase

import androidx.paging.PagingData
import com.example.baseproject.model.entity.Breed
import com.example.baseproject.repository.cats.CatBreedRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

class GetCatBreedsUseCase internal constructor(private val catBreedRepository: CatBreedRepository) {

    operator fun invoke(): Flowable<PagingData<Breed>> {
        return catBreedRepository.getBreeds()
    }
}