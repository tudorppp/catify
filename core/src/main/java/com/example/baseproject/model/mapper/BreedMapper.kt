package com.example.baseproject.model.mapper

import com.example.baseproject.model.dto.BreedDTO
import com.example.baseproject.model.entity.Breed

internal fun BreedDTO.toEntity(): Breed {
    return Breed(
        id = this.id,
        imageUrl = this.image?.url,
        name = this.name,
        description = this.description,
        countryCode = this.countryCode,
        temperament = this.temperament,
        wikipediaLink = this.wikipediaLink
    )
}