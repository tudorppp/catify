package com.example.baseproject.home.breed_detail

import com.example.baseproject.model.entity.Breed

data class BreedDetailUiModel(
    val id: String,
    val name: String,
    val description: String,
    val countryCode: String,
    val wikipediaUrl: String?,
    val imageUrl: String?,
    val temperament: List<String>
)

fun Breed.toUiModel(): BreedDetailUiModel {
    return BreedDetailUiModel(
        id = this.id,
        name = this.name,
        description = this.description,
        countryCode = this.countryCode,
        wikipediaUrl = this.wikipediaLink,
        imageUrl = this.imageUrl,
        temperament = this.temperament.separateStringIntoList()
    )
}

private const val SEPARATOR = ", "

private fun String.separateStringIntoList(): List<String> {
    return this.split(SEPARATOR)
}