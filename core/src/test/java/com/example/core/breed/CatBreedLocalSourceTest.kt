package com.example.core.breed

import com.example.baseproject.model.entity.Breed
import com.example.baseproject.repository.breed.CatBreedLocalSource
import com.example.baseproject.repository.breed.CatBreedLocalSourceImpl
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.assertj.core.api.Assertions.assertThat
import org.koin.test.inject

class CatBreedLocalSourceTest : KoinTest {

    @get:Rule
    val rule = KoinTestRule.create {
        modules(
            module {
                single<CatBreedLocalSource> { CatBreedLocalSourceImpl() }
            }
        )
    }

    private val localSource by inject<CatBreedLocalSource>()

    @Test
    fun `test breed is successfully stored`() {
        // Given
        val breed = Breed(
            id = "siam",
            imageUrl = "http://some-image.ro",
            name = "Siamese",
            description = "Best cat in the world",
            countryCode = "RO",
            temperament = "Agressive, Easy going",
            wikipediaLink = null
        )
        // When
        localSource.storeBreed(breed)
        // Then
        assertThat(localSource.getBreedById("siam")).isEqualTo(breed)
    }

}