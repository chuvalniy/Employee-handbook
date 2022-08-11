package com.example.feature.presentation.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.ui.BaseFragment
import com.example.feature.R
import com.example.feature.databinding.FragmentHomeBinding
import com.example.feature.presentation.home.epoxy.HomeEpoxyController
import com.example.feature.presentation.home.view_model.HomeViewModel
import com.example.feature.presentation.home.view_model.UiEvent
import com.example.feature.presentation.home.view_model.UiSideEffect
import com.example.feature.presentation.home.view_model.UiState
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by sharedViewModel<HomeViewModel>()

    private var epoxyController: HomeEpoxyController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEpoxyController()

        observeUiState()
        observeUiEffects()

        processUiEvents()
    }

    private fun processUiEvents() {
        binding.btnTryAgain.setOnClickListener {
            viewModel.onEvent(UiEvent.TryAgainButtonClicked)
        }
    }

    private fun setupEpoxyController() {
        epoxyController = HomeEpoxyController(
            onUserSearch = { viewModel.onEvent(UiEvent.SearchQueryChanged(it)) },
            onMoveToDetail = { viewModel.onEvent(UiEvent.UserItemClicked(it)) },
            onSelectDepartment = { viewModel.onEvent(UiEvent.DepartmentSelected(it)) }
        ).also {
            binding.epoxyRecyclerView.setController(it)
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                processUiState(state)
            }
        }
    }

    private fun processUiState(state: UiState) {
        epoxyController?.setData(state)
        binding.layoutErrorState.isVisible = state.error != null
        binding.epoxyRecyclerView.isVisible = state.error == null
    }

    private fun observeUiEffects() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEffect.collect { effect ->
                when (effect) {
                    is UiSideEffect.NavigateToDetails -> {
                        val action = HomeFragmentDirections.actionHomeToDetails(effect.user)
                        findNavController().navigate(action)
                    }
                    is UiSideEffect.ShowFilterDialog -> {
                        findNavController().navigate(R.id.action_home_to_filter_dialog)
                    }
                    is UiSideEffect.ShowSnackbar -> {

                    }
                }
            }
        }
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

}