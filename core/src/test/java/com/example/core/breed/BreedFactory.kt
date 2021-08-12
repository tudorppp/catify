package com.example.core.breed

import com.example.baseproject.model.dto.BreedDTO
import java.util.concurrent.atomic.AtomicInteger

class BreedFactory {
    private val counter = AtomicInteger(0)

    internal fun createBreed(): BreedDTO {
        val id = counter.incrementAndGet()
        val breed = BreedDTO(
            id = "id_$id",
            image = null,
            name = "name_$id",
            description = "Best cat ever",
            temperament = "Nice, Agile",
            countryCode = "US",
            wikipediaLink = null
        )
        return breed
    }
}