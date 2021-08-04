package com.example.baseproject.shared

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.baseproject.R
import com.example.baseproject.login.LOGIN_STATE
import com.example.baseproject.login.LoginState
import com.example.baseproject.repository.authentication.AuthManager
import javax.inject.Inject

abstract class RequireLoginBaseFragment<VB : ViewDataBinding, VM : ViewModel>(@LayoutRes private val resId: Int) :
    BaseFragment<VB, VM>(resId) {

    @Inject
    lateinit var authManager: AuthManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkLoginStateAndRedirectIfNecessary()
        observeLoginState()

    }

    private fun checkLoginStateAndRedirectIfNecessary() {
        if (!authManager.getLoginStatus()) {
            findNavController().navigate(R.id.loginFragment)
        }
    }

    private fun observeLoginState() {
        val savedStateHandle = findNavController().currentBackStackEntry?.savedStateHandle
        savedStateHandle?.getLiveData<LoginState>(LOGIN_STATE)?.observe(viewLifecycleOwner) {

        }
    }
}