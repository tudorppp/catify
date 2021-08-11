package com.example.baseproject.home

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.baseproject.HomeFragmentBinding
import com.example.baseproject.R
import com.example.baseproject.errors.ErrorType
import com.example.baseproject.errors.asError
import com.example.baseproject.shared.RequireLoginBaseFragment
import com.example.baseproject.util.MarginItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class HomeFragment : RequireLoginBaseFragment<HomeFragmentBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModel()

    private lateinit var adapter: CatBreedAdapter
    private lateinit var loaderAdapter: LoaderStateAdapter

    override fun doIfUserIsLoggedIn(view: View, savedInstanceState: Bundle?) {
        prepareReturnTransition(view)
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
    }

    private fun prepareReturnTransition(view: View) {
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    private fun initAdapters() {
        adapter = CatBreedAdapter(::onBreedClicked).also {
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

    private fun onBreedClicked(cardView: View, breedId: String) {
        val breedDetailCardTransitionName = getString(R.string.breed_card_transition_name)
        val extras = FragmentNavigatorExtras(cardView to breedDetailCardTransitionName)
        val directions = HomeFragmentDirections.actionHomeFragmentToBreedDetailFragment(breedId)
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        findNavController().navigate(directions, extras)
    }

    private fun displayError(error: ErrorType) {
        when (error) {
            is ErrorType.Api -> showSnackBar(error.message ?: "", Snackbar.LENGTH_SHORT)
            ErrorType.NoInternet -> showSnackBar(R.string.no_internet_error, Snackbar.LENGTH_SHORT)
            ErrorType.Unknown -> showSnackBar(R.string.unknown_error, Snackbar.LENGTH_SHORT)
        }
    }
}