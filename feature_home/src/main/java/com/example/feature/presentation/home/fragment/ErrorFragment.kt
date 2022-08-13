package com.example.feature.presentation.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.ui.BaseFragment
import com.example.feature.R
import com.example.feature.databinding.FragmentErrorBinding
import com.example.feature.presentation.home.model.UiEvent
import com.example.feature.presentation.home.model.UiSideEffect
import com.example.feature.presentation.home.view_model.HomeViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ErrorFragment : BaseFragment<FragmentErrorBinding>() {

    private val viewModel by sharedViewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processUiEvent()

        observeUiEffect()
    }

    private fun observeUiEffect() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEffect.collect { effect ->
                when (effect) {
                    is UiSideEffect.NavigateToHomeScreen -> {
                        findNavController().navigate(R.id.action_init_error_to_home_)
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun processUiEvent() {
        binding.btnTryAgain.setOnClickListener {
            viewModel.onEvent(UiEvent.TryAgainButtonClicked)
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentErrorBinding.inflate(inflater, container, false)
}