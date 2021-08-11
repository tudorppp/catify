package com.example.baseproject.repository.cats

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.baseproject.model.entity.Breed
import io.reactivex.rxjava3.core.Flowable

private const val PAGE_SIZE = 20
private const val INITIAL_PAGE_SIZE = 10

internal interface CatBreedRepository {

    fun getBreeds(pageConfig: PagingConfig = getDefaultPageConfig()): Flowable<PagingData<Breed>>

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = INITIAL_PAGE_SIZE,
            enablePlaceholders = true
        )
    }
}