package com.example.baseproject.feature.home

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import com.example.baseproject.R
import com.example.baseproject.errors.ErrorType
import com.example.baseproject.errors.asError
import com.example.baseproject.shared.BaseRxViewModel
import com.example.baseproject.shared.Event
import com.example.baseproject.usecase.GetCatBreedsUseCase
import com.example.baseproject.usecase.LogoutUsernameUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeViewModel(
    private val getCatBreedsUseCase: GetCatBreedsUseCase,
    private val logoutUsernameUseCase: LogoutUsernameUseCase,
    private val resources: Resources
) : BaseRxViewModel() {

    private val _catBreeds = MutableLiveData<PagingData<CatBreedUIModel>>()
    val catBreeds: LiveData<PagingData<CatBreedUIModel>> = _catBreeds

    private val _state = MutableLiveData<State>().apply { value = State.Loading }

    private val _logoutState = MutableLiveData<Event<State.Logout>>()
    val logoutState: LiveData<Event<State.Logout>> = _logoutState

    val isLoading: LiveData<Boolean> = _state.map { it == State.Loading }

    val isLoaded: LiveData<Boolean> = _state.map { it == State.Loaded }

    val isError: LiveData<Boolean> = _state.map { it == State.Error }

    init {
        fetchCats()
    }

    private fun fetchCats() {
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

    fun logout() {
        disposable.add(
            logoutUsernameUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _logoutState.value = Event(State.Logout.Success)
                }, {
                    val errorMessage = getErrorMessage(it.asError())
                    _logoutState.value = Event(State.Logout.Failed(errorMessage))
                })
        )
    }

    fun setState(state: State) {
        _state.value = state
    }

    private fun getErrorMessage(errorType: ErrorType): String {
        return when (errorType) {
            is ErrorType.Api -> errorType.message ?: ""
            ErrorType.NoInternet -> resources.getString(R.string.no_internet_error)
            ErrorType.Unknown -> resources.getString(R.string.unknown_error)
        }
    }

    sealed class State {
        object Loading : State()
        object Loaded : State()
        object Error : State()

        sealed class Logout : State() {
            object Success : Logout()
            data class Failed(val errorMessage: String) : Logout()
        }
    }
}