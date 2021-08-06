package com.example.baseproject.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.baseproject.HomeFragmentBinding
import com.example.baseproject.R
import com.example.baseproject.shared.RequireLoginBaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : RequireLoginBaseFragment<HomeFragmentBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel : HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("dada", viewModel.toString())
    }

}