package com.example.baseproject.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baseproject.databinding.ItemBreedLoaderBinding

class LoaderStateAdapter(
    private val retry: () -> Unit,
    private val onError: (LoadState.Error) -> Unit
) : LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        return LoaderViewHolder(
            ItemBreedLoaderBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retry,
            onError
        )
    }

    class LoaderViewHolder(
        private val binding: ItemBreedLoaderBinding,
        retry: () -> Unit,
        private val onError: (LoadState.Error) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retry.setOnClickListener { retry() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Loading) {
                binding.motionLayout.transitionToEnd()
            } else {
                onError(loadState as LoadState.Error)
                binding.motionLayout.transitionToStart()
            }
        }
    }

}