package com.example.baseproject.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import com.example.baseproject.shared.BaseRxViewModel
import com.example.baseproject.usecase.GetCatBreedsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

class HomeViewModel(private val getCatBreedsUseCase: GetCatBreedsUseCase) : BaseRxViewModel() {

    private val _catBreeds = MutableLiveData<PagingData<CatBreedUIModel>>()
    val catBreeds: LiveData<PagingData<CatBreedUIModel>> = _catBreeds

    private val _state = MutableLiveData<State>().apply { value = State.Loading }

    val isLoading: LiveData<Boolean> = _state.map { it == State.Loading }

    val isLoaded: LiveData<Boolean> = _state.map { it == State.Loaded }

    val isError: LiveData<Boolean> = _state.map { it == State.Error }

    @ExperimentalCoroutinesApi
    fun fetchCats() {
        _state.value = State.Loading
        disposable.add(
            getCatBreedsUseCase()
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.map { breed ->
                        breed.toUiModel()
                    }
                }
                .cachedIn(viewModelScope)
                .subscribe {
                    _catBreeds.value = it
                })
    }

    fun setState(state: State) {
        _state.value = state
    }

    sealed class State {
        object Loading : State()
        object Loaded : State()
        object Error : State()
    }
}