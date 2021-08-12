package com.example.baseproject.home.breed_detail

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.baseproject.BreedDetailFragmentBinding
import com.example.baseproject.R
import com.example.baseproject.shared.BaseFragment
import com.google.android.material.transition.MaterialContainerTransform
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class BreedDetailFragment :
    BaseFragment<BreedDetailFragmentBinding, BreedDetailViewModel>(R.layout.fragment_breed_detail) {

    private val args: BreedDetailFragmentArgs by navArgs()
    override val viewModel: BreedDetailViewModel by viewModel { parametersOf(args.breedId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT

            setAllContainerColors(getColorSurfaceColor())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            uiModel.observe(viewLifecycleOwner) {
                binding.uiModel = it
            }
        }
    }

    private fun getColorSurfaceColor(): Int {
        val typedValue = TypedValue()
        val theme = requireContext().theme
        theme.resolveAttribute(R.attr.colorSurface, typedValue, true)
        return typedValue.data
    }

}