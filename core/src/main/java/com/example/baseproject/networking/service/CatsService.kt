package com.example.baseproject.networking.service

import com.example.baseproject.model.dto.BreedDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CatsService {

    @GET("breeds")
    fun getBreeds(@Query("page") page: Int, @Query("limit") limit: Int): Single<List<BreedDTO>>

}