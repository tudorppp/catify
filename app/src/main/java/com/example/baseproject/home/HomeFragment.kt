package com.example.baseproject.home

import androidx.paging.LoadState
import com.example.baseproject.HomeFragmentBinding
import com.example.baseproject.R
import com.example.baseproject.errors.ErrorType
import com.example.baseproject.errors.asError
import com.example.baseproject.shared.RequireLoginBaseFragment
import com.example.baseproject.util.MarginItemDecoration
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : RequireLoginBaseFragment<HomeFragmentBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModel()

    private lateinit var adapter: CatBreedAdapter
    private lateinit var loaderAdapter: LoaderStateAdapter

    @ExperimentalCoroutinesApi
    override fun doIfUserIsLoggedIn(configurationChanged: Boolean) {
        initAdapters()
        with(binding) {
            recyclerView.adapter = adapter.withLoadStateFooter(loaderAdapter)
            recyclerView.addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.s3x).toInt()))
            retryButton.setOnClickListener {
                adapter.refresh()
            }
        }
        with(viewModel) {
            catBreeds.observe(viewLifecycleOwner) {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        if (!configurationChanged) {
            viewModel.fetchCats()
        }
    }

    private fun initAdapters() {
        adapter = CatBreedAdapter().also {
            it.addLoadStateListener { loadState ->
                when (val result = loadState.source.refresh) {
                    is LoadState.NotLoading -> viewModel.setState(HomeViewModel.State.Loaded)
                    is LoadState.Loading -> viewModel.setState(HomeViewModel.State.Loading)
                    is LoadState.Error -> {
                        displayError(result.error.asError())
                        viewModel.setState(HomeViewModel.State.Error)
                    }
                    else -> {
                    }
                }
            }
        }
        loaderAdapter = LoaderStateAdapter(
            { adapter.retry() },
            { displayError(it.error.asError()) }
        )
    }

    private fun displayError(error: ErrorType) {
        when (error) {
            is ErrorType.Api -> showSnackBar(error.message ?: "", Snackbar.LENGTH_SHORT)
            ErrorType.NoInternet -> showSnackBar(R.string.no_internet_error, Snackbar.LENGTH_SHORT)
            ErrorType.Unknown -> showSnackBar(R.string.unknown_error, Snackbar.LENGTH_SHORT)
        }
    }
}