package com.example.core.breed

import com.example.baseproject.model.dto.BreedDTO
import com.example.baseproject.networking.service.CatsService
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Query

internal class FakeCatsApi : CatsService {

    private val models = arrayListOf<BreedDTO>()
    var failureMessage: String? = null

    fun addBreed(breedDTO: BreedDTO) {
        models.add(breedDTO)
    }

    override fun getBreeds(@Query("page") page: Int, @Query("limit") limit: Int): Single<List<BreedDTO>> {
        failureMessage?.let {
            return Single.error(Throwable(it))
        }
        return Single.just(findBreeds(page, limit))
    }

    private fun findBreeds(page: Int, limit: Int): List<BreedDTO> {
        val startingFrom = (page - 1) * limit
        if (startingFrom >= models.size) return arrayListOf()
        val until = (page - 1) * limit + limit
        val untilConsideringSize = if (until >= models.size) models.size else until
        return models.subList(startingFrom, untilConsideringSize)
    }

}