package com.example.baseproject.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.baseproject.LoginFragmentBinding
import com.example.baseproject.R
import com.example.baseproject.shared.BaseFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>(R.layout.fragment_login) {

    override val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
        savedStateHandle?.set(LOGIN_STATE, LoginState.LoginFailed.toString())

        with(viewModel) {
            state.observe(viewLifecycleOwner) {
                when (val result = it.consume()) {
                    is LoginViewModel.State.LoginFailed -> {
                        if (result.errorId != null) {
                            showSnackBar(result.errorId, Snackbar.LENGTH_SHORT)
                        } else if (result.errorMessage != null) {
                            showSnackBar(result.errorMessage, Snackbar.LENGTH_SHORT)
                        }
                        savedStateHandle?.set(LOGIN_STATE, LoginState.LoginFailed)
                    }
                    LoginViewModel.State.LoginSucceeded -> {
                        savedStateHandle?.set(LOGIN_STATE, LoginState.LoginSuccessful)
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}