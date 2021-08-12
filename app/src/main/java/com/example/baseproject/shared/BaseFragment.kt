package com.example.baseproject.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.baseproject.BR
import com.example.baseproject.R
import com.example.baseproject.util.autoCleared
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel>(@LayoutRes private val resId: Int) : Fragment() {

    var binding by autoCleared<VB>()

    protected abstract val viewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        DataBindingUtil.inflate<VB>(layoutInflater, resId, null, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.setVariable(BR.viewModel, viewModel)
            binding = it
        }.root

    protected fun showSnackBar(
        @StringRes textRes: Int,
        duration: Int = Snackbar.LENGTH_LONG,
        anchorView: View? = null
    ) {
        showSnackBar(getString(textRes), duration, anchorView)
    }

    protected fun showSnackBar(text: String, duration: Int = Snackbar.LENGTH_LONG, anchorView: View? = null) {
        Snackbar.make(binding.root, text, duration).setAnchorView(anchorView).show()
    }

    private inline fun showSnackBar(
        text: String,
        @StringRes actionRes: Int = R.string.snackbar_retry,
        anchorView: View?,
        crossinline action: () -> Unit
    ) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_LONG).setAction(actionRes) { action() }
            .setAnchorView(anchorView).show()
    }

}