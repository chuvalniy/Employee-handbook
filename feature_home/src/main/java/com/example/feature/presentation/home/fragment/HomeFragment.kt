package com.example.feature.presentation.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.ui.*
import com.example.feature.R
import com.example.feature.databinding.FragmentHomeBinding
import com.example.feature.domain.model.DepartmentList
import com.example.feature.presentation.home.epoxy.HomeEpoxyController
import com.example.feature.presentation.home.model.UiEvent
import com.example.feature.presentation.home.model.UiSideEffect
import com.example.feature.presentation.home.model.UiState
import com.example.feature.presentation.home.view_model.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by sharedViewModel<HomeViewModel>()

    private var epoxyController: HomeEpoxyController? = null
    private var snackbar: Snackbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEpoxyController()
        setupTabLayout()

        observeUiState()
        observeUiEffects()

        processUiEvents()
        processSearchView()
    }

    private fun setupTabLayout() {
        DepartmentList.departmentListUi.onEach {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(it))
        }
    }

    private fun processSearchView() {
        binding.searchView.setOnQueryTextFocusChangeListener { view, hasFocus ->
            binding.btnSort.isVisible = !hasFocus
            binding.btnCancel.isVisible = hasFocus
            binding.btnCancel.setOnClickListener {
                view.clearFocus()
                binding.searchView.setQuery("", false)
            }
        }
    }

    private fun processUiEvents() {
        binding.apply {
            searchView.onQueryTextChanged {
                viewModel.onEvent(UiEvent.SearchQueryChanged(it))
            }

            tabLayout.onTabSelected {
                viewModel.onEvent(UiEvent.DepartmentSelected(DepartmentList.departmentListDatabase[it]))
            }

            btnSort.setOnClickListener {
                viewModel.onEvent(UiEvent.FilterButtonClicked)
            }

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.onEvent(UiEvent.ScreenRefreshed)
            }
        }
    }

    private fun setupEpoxyController() {
        epoxyController = HomeEpoxyController(
            onMoveToDetail = { viewModel.onEvent(UiEvent.UserItemClicked(it)) },
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
        binding.layoutSearchError.isVisible =
            !state.isLoading && state.data.isEmpty() && state.searchQuery.isNotEmpty()

        binding.swipeRefreshLayout.isRefreshing = state.fetchFromRemote && !state.isInit

        val tab = binding.tabLayout.getTabAt(DepartmentList.departmentListDatabase.indexOf(state.departmentFilter))
        tab?.select()

        if (state.isLoading && !state.isInit) {
            Log.d("TAGTAG" ,"asdads")
            snackbar = requireContext().getSnackBar(binding.root, "Loading...")
        } else snackbar?.dismiss()
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
                        requireContext().showSnackBar(binding.root, "Error")
                    }
                    is UiSideEffect.NavigateToErrorScreen -> {
                        findNavController().navigate(R.id.action_home__to_init_error)
                    }
                    else -> Unit
                }
            }
        }
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onDestroy() {
        super.onDestroy()
        snackbar = null
    }
}