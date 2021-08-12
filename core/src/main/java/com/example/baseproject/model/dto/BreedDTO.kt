package com.example.baseproject.model.dto

import com.google.gson.annotations.SerializedName

internal data class BreedDTO(
    val id: String,
    val image: ImageDTO?,
    val name: String,
    val description: String,
    val temperament: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("wikipedia_url") val wikipediaLink: String?
)