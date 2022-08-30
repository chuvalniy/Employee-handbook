package com.example.feature.presentation.home.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.ui.*
import com.example.core_navigation.NavCommand
import com.example.core_navigation.NavCommands
import com.example.core_navigation.navigate
import com.example.feature.R
import com.example.feature.databinding.FragmentHomeBinding
import com.example.feature.domain.model.DepartmentList
import com.example.feature.presentation.home.epoxy.HomeEpoxyController
import com.example.feature.presentation.home.model.HomeEffect
import com.example.feature.presentation.home.model.HomeEvent
import com.example.feature.presentation.home.model.HomeState
import com.example.feature.presentation.home.model.LoadingState
import com.example.feature.presentation.home.view_model.HomeViewModel
import com.example.feature.presentation.home.view_model.HomeViewModelFactory
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var factory: HomeViewModelFactory.Factory

    private var epoxyController: HomeEpoxyController? = null
    private var snackbar: Snackbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEpoxyController()
        setupTabLayout()

        observeUiState()
        observeUiEffects()

        processUiEvent()
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

    private fun processUiEvent() {
        binding.apply {
            searchView.onQueryTextChanged {
                viewModel.onEvent(HomeEvent.SearchQueryChanged(it))
            }

            tabLayout.onTabSelected {
                viewModel.onEvent(HomeEvent.DepartmentSelected(DepartmentList.departmentListDatabase[it]))
            }

            btnSort.setOnClickListener {
                viewModel.onEvent(HomeEvent.FilterButtonClicked)
            }

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.onEvent(HomeEvent.ScreenRefreshed)
            }
        }
    }

    private fun setupEpoxyController() {
        epoxyController = HomeEpoxyController(
            onMoveToDetail = { viewModel.onEvent(HomeEvent.UserItemClicked(it)) },
        ).also {
            binding.epoxyRecyclerView.setController(it)
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                processUiState(state)
            }
        }
    }

    private fun processUiState(state: HomeState) {
        epoxyController?.setData(state)

        binding.layoutSearchError.isVisible =
            state.loadingState == LoadingState.NONE && state.data.isEmpty() && state.searchQuery.isNotEmpty()

        binding.swipeRefreshLayout.isRefreshing = state.isRefreshing

        val tab =
            binding.tabLayout.getTabAt(DepartmentList.departmentListDatabase.indexOf(state.departmentFilter))
        tab?.select()

        if (state.loadingState == LoadingState.SNACKBAR) {
            snackbar =
                requireContext().getSnackBar(binding.root, getString(R.string.loading_message))
        } else snackbar?.dismiss()
    }

    private fun observeUiEffects() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is HomeEffect.NavigateToDetails -> {
                    val action = HomeFragmentDirections.actionHomeToDetails(effect.user)
                    findNavController().navigate(action)
                }
                is HomeEffect.ShowFilterDialog ->
                    findNavController().navigate(R.id.action_home_to_filter_dialog)
                is HomeEffect.ShowSnackbar -> requireContext().showSnackBar(
                    binding.root,
                    effect.message.asString(requireContext())
                )
                is HomeEffect.NavigateToErrorScreen -> navigateToError()
            }
        }
    }

    private fun navigateToError() {
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    url = Uri.parse("myApp://fragmentError"),
                    isModal = true,
                    isSingleTop = true
                )
            )
        )
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