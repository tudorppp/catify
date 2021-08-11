package com.example.baseproject.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.baseproject.databinding.ItemCatBinding

class CatBreedAdapter : PagingDataAdapter<CatBreedUIModel, RecyclerView.ViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? CatBreedViewHolder)?.bind(item = getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CatBreedViewHolder(ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            .also {
                //TODO here set the click listener
            }
    }

    private class CatBreedViewHolder(private val binding: ItemCatBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CatBreedUIModel?) {
            item?.let {
                binding.catModelUi = it
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CatBreedUIModel>() {
            override fun areItemsTheSame(oldItem: CatBreedUIModel, newItem: CatBreedUIModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CatBreedUIModel, newItem: CatBreedUIModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}