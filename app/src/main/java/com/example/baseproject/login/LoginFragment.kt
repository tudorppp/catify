package com.example.baseproject.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.baseproject.LoginFragmentBinding
import com.example.baseproject.R
import com.example.baseproject.shared.BaseFragment

class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>(R.layout.fragment_login) {

    override val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
        savedStateHandle?.set(LOGIN_STATE, LoginState.LoginFailed)

        with(viewModel) {
            loginState.observe(viewLifecycleOwner) {
                savedStateHandle?.set(LOGIN_STATE, it)
                when (it) {
                    LoginState.LoginFailed -> TODO()
                    LoginState.LoginSuccessful -> navController.popBackStack()
                }
            }
        }
    }
}