package com.example.baseproject.repository.breed

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.baseproject.model.dto.BreedDTO
import com.example.baseproject.networking.service.CatsService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

private const val FIRST_PAGE = 1

internal class CatBreedRemotePagingSource(private val catsService: CatsService) :
    RxPagingSource<Int, BreedDTO>() {

    override fun getRefreshKey(state: PagingState<Int, BreedDTO>): Int? {
        return state.anchorPosition
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, BreedDTO>> {
        val page = params.key ?: FIRST_PAGE
        return catsService
            .getBreeds(page, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map<LoadResult<Int, BreedDTO>> {
                LoadResult.Page(
                    data = it,
                    prevKey = if (page == FIRST_PAGE) null else page - 1,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }
}

