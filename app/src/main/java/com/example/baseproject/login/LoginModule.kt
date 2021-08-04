package com.example.baseproject.login

import androidx.lifecycle.ViewModel
import com.example.baseproject.di.ViewModelBuilder
import com.example.baseproject.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class LoginModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun addLoginFragment(): LoginFragment

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindViewModel(viewModel: LoginViewModel): ViewModel

}