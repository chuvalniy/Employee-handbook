package com.example.feature.presentation.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.feature.databinding.FragmentDialogBinding
import com.example.feature.presentation.home.model.HomeEvent
import com.example.feature.presentation.home.model.HomeState
import com.example.feature.presentation.home.model.SortType
import com.example.feature.presentation.home.view_model.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilterBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()

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

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                processUiState(state)
            }
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