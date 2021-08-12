package com.example.baseproject.repository.breed

import com.example.baseproject.model.entity.Breed

internal class CatBreedLocalSourceImpl : CatBreedLocalSource {

    private val breeds: MutableList<Breed> = arrayListOf()

    override fun getBreedById(breedId: String): Breed {
        return breeds.first { it.id == breedId }
    }

    override fun storeBreed(newBreed: Breed) {
        breeds.firstOrNull { it.id == newBreed.id } ?: breeds.add(newBreed)
    }
}