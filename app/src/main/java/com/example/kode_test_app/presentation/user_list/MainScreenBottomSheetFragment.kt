package com.example.kode_test_app.presentation.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.kode_test_app.core.utils.SortType
import com.example.kode_test_app.databinding.FragmentDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainScreenBottomSheetFragment: BottomSheetDialogFragment() {

    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainScreenViewModel by activityViewModels()

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

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.sortType.collect { sort ->
                when (sort) {
                    SortType.BY_NAME -> binding.rbSortByAlphabet.isChecked
                    SortType.BY_DATE -> binding.rbSortByBirthday.isChecked
                }
            }
        }

        binding.rbSortByAlphabet.setOnClickListener {
            viewModel.sortType.value = SortType.BY_NAME
        }
        binding.rbSortByBirthday.setOnClickListener {
            viewModel.sortType.value = SortType.BY_DATE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}