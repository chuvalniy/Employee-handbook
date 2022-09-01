package com.example.feature.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import com.example.core.utils.SortType
import com.example.feature.R
import com.example.feature.databinding.FragmentDialogBinding
import com.example.feature.di.HomeComponentViewModel
import com.example.feature.model.HomeEvent
import com.example.feature.model.HomeState
import com.example.feature.view_model.HomeViewModel
import com.example.feature.view_model.HomeViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.Lazy
import javax.inject.Inject

class FilterBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!

    @Inject
    internal lateinit var factory: Lazy<HomeViewModelFactory>

    private val viewModel: HomeViewModel by navGraphViewModels(R.id.home_nav_graph) {
        factory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<HomeComponentViewModel>()
            .homeComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUiState()

        processUiEvent()
    }

    private fun processUiEvent() {
        binding.rbSortByBirthday.setOnClickListener {
            viewModel.onEvent(HomeEvent.SortTypeSelected(SortType.BY_DATE))
        }
        binding.rbSortByAlphabet.setOnClickListener {
            viewModel.onEvent(HomeEvent.SortTypeSelected(SortType.BY_NAME))
        }
    }

    private fun observeUiState() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.state.collect { state ->
            processUiState(state)
        }
    }

    private fun processUiState(state: HomeState) {
        when (state.sortType) {
            SortType.BY_NAME -> {
                binding.rbSortByAlphabet.isChecked = true
            }
            SortType.BY_DATE -> {
                binding.rbSortByBirthday.isChecked = true
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}