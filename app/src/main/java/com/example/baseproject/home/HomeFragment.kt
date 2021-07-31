package com.example.baseproject.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.baseproject.HomeFragmentBinding
import com.example.baseproject.R
import com.example.baseproject.shared.BaseFragment

class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}