package com.example.baseproject.repository.breed

import com.example.baseproject.model.entity.Breed

internal interface CatBreedLocalSource {

    fun getBreedById(breedId: String): Breed

    fun storeBreed(newBreed: Breed)

}