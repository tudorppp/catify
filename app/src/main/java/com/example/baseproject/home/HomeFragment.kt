package com.example.baseproject.home

import com.example.baseproject.HomeFragmentBinding
import com.example.baseproject.R
import com.example.baseproject.shared.RequireLoginBaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : RequireLoginBaseFragment<HomeFragmentBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel : HomeViewModel by viewModel()

    override fun doIfUserIsLoggedIn() {
        TODO("Not yet implemented")
    }

}