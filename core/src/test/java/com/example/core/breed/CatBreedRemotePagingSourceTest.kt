package com.example.core.breed

import androidx.paging.PagingSource.LoadParams.Refresh
import androidx.paging.PagingSource.LoadResult.Page
import com.example.baseproject.model.dto.BreedDTO
import com.example.baseproject.repository.breed.CatBreedRemotePagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CatBreedRemotePagingSourceTest {
    private val breedFactory = BreedFactory()
    private val fakeBreeds: List<BreedDTO> = (1..30).map {
        breedFactory.createBreed()
    }
    private val fakeCatsApi = FakeCatsApi().apply {
        fakeBreeds.forEach { addBreed(it) }
    }

    @Test
    fun `pageKeyedCatBreedPagingSource returns right values`() {
        // Given
        val pagingSource = CatBreedRemotePagingSource(fakeCatsApi)
        val refreshRequest = Refresh<Int>(null, 13, false)

        // When
        pagingSource.loadSingle(refreshRequest)
            .test()
            .await()

            // Then
            .assertValueCount(1)
            .assertValue(
                Page(fakeBreeds.subList(0, 13), null, 2)
            )
    }
}