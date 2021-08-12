package com.example.baseproject.repository.breed

import androidx.paging.*
import androidx.paging.rxjava3.flowable
import com.example.baseproject.model.entity.Breed
import com.example.baseproject.model.mapper.toEntity
import com.example.baseproject.networking.service.CatsService
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi

internal class CatBreedRepositoryImpl(
    private val catsService: CatsService,
    private val catBreedLocalSource: CatBreedLocalSource
) :
    CatBreedRepository {

    override fun getBreedById(breedId: String): Breed {
        return catBreedLocalSource.getBreedById(breedId)
    }

    @ExperimentalCoroutinesApi
    override fun getBreeds(pageConfig: PagingConfig): Flowable<PagingData<Breed>> {
        return Pager(
            config = pageConfig,
            pagingSourceFactory = { CatBreedRemotePagingSource(catsService) }
        ).flowable
            .map {
                it.map { dto ->
                    val entity = dto.toEntity()
                    catBreedLocalSource.storeBreed(entity)
                    entity
                }
            }
    }
}