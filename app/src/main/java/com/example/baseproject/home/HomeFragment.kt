package com.example.baseproject.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.baseproject.HomeFragmentBinding
import com.example.baseproject.R
import com.example.baseproject.shared.BaseFragment
import com.example.baseproject.shared.RequireLoginBaseFragment

class HomeFragment : RequireLoginBaseFragment<HomeFragmentBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("dada", viewModel.toString())
    }

}