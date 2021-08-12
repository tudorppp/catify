package com.example.baseproject.home.breed_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baseproject.usecase.GetBreedByIdUseCase

class BreedDetailViewModel(private val breedId: String, private val getBreedByIdUseCase: GetBreedByIdUseCase) :
    ViewModel() {

    private val _uiModel = MutableLiveData<BreedDetailUiModel>()
    val uiModel: LiveData<BreedDetailUiModel> = _uiModel

    init {
        getBreedById()
    }

    private fun getBreedById() {
        _uiModel.value = getBreedByIdUseCase(breedId).toUiModel()
    }
}