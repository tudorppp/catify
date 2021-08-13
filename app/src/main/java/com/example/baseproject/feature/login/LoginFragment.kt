package com.example.baseproject.feature.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.baseproject.LoginFragmentBinding
import com.example.baseproject.R
import com.example.baseproject.shared.BaseFragment
import com.example.baseproject.util.closeKeyboard
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>(R.layout.fragment_login) {

    override val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
        savedStateHandle?.set(LOGIN_STATE, LoginState.LoginFailed)

        with(viewModel) {
            state.observe(viewLifecycleOwner) {
                when (val result = it.consume()) {
                    is LoginViewModel.State.LoginFailed -> {
                        showSnackBar(result.errorMessage, Snackbar.LENGTH_SHORT)
                    }
                    LoginViewModel.State.LoginSucceeded -> {
                        binding.loginButton.closeKeyboard()
                        savedStateHandle?.set(LOGIN_STATE, LoginState.LoginSuccessful)
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}