package com.example.baseproject.usecase

import com.example.baseproject.model.entity.Breed
import com.example.baseproject.repository.breed.CatBreedRepository

class GetBreedByIdUseCase internal constructor(private val catBreedRepository: CatBreedRepository) {

    operator fun invoke(breedId: String): Breed {
        return catBreedRepository.getBreedById(breedId)
    }
}