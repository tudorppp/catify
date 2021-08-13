package com.example.baseproject.feature.home

import com.example.baseproject.model.entity.Breed

data class CatBreedUIModel(val id: String, val imageUrl: String?, val name: String, val description: String)

fun Breed.toUiModel(): CatBreedUIModel {
    return CatBreedUIModel(
        id = this.id,
        imageUrl = this.imageUrl,
        name = this.name,
        description = this.description
    )
}