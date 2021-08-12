package com.example.baseproject.shared

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.baseproject.LoginDirections
import com.example.baseproject.login.LOGIN_STATE
import com.example.baseproject.login.LoginState
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class RequireLoginBaseFragment<VB : ViewDataBinding, VM : ViewModel>(@LayoutRes private val resId: Int) :
    BaseFragment<VB, VM>(resId) {

    private val requireLoginViewModel: RequireLoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLoginState()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (requireLoginViewModel.isUserLoggedIn())
            doIfUserIsLoggedIn(view, savedInstanceState)
        else
            NavHostFragment.findNavController(this@RequireLoginBaseFragment)
                .navigate(LoginDirections.actionToLoginFragment())
    }

    private fun observeLoginState() {
        val currentBackStackEntry = findNavController().currentBackStackEntry
        val savedStateHandle = currentBackStackEntry?.savedStateHandle
        savedStateHandle?.getLiveData<LoginState>(LOGIN_STATE)?.observe(currentBackStackEntry) {
            when (it) {
                LoginState.LoginSuccessful -> {
                }
                else -> {
                    activity?.finish()
                }
            }
        }
    }

    protected abstract fun doIfUserIsLoggedIn(view: View, savedInstanceState: Bundle?)
}