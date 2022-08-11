package com.example.feature.presentation.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.airbnb.epoxy.EpoxyController
import com.example.core.ui.BaseFragment
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
        observeUiEffect()
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
    }

    private fun observeUiEffect() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEffect.collect { effect ->
                when (effect) {
                    is UiSideEffect.NavigateToDetails -> TODO()
                    is UiSideEffect.ShowFilterDialog -> TODO()
                    is UiSideEffect.ShowSnackbar -> TODO()
                }
            }
        }
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

}