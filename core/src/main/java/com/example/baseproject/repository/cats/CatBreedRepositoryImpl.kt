package com.example.baseproject.repository.cats

import androidx.paging.*
import androidx.paging.rxjava3.flowable
import androidx.paging.rxjava3.observable
import com.example.baseproject.model.entity.Breed
import com.example.baseproject.model.mapper.toEntity
import com.example.baseproject.networking.service.CatsService
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi

internal class CatBreedRepositoryImpl(private val catsService: CatsService) :
    CatBreedRepository {

    @ExperimentalCoroutinesApi
    override fun getBreeds(pageConfig: PagingConfig): Flowable<PagingData<Breed>> {
        return Pager(
            config = pageConfig,
            pagingSourceFactory = { CatBreedRemotePagingSource(catsService) }
        ).flowable
            .map {
                it.map { dto ->
                    dto.toEntity()
                }
            }
    }
}