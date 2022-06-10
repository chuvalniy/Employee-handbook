package com.example.feature.presentation.user_main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.feature.databinding.FragmentDialogBinding
import com.example.feature.presentation.user_main.view_model.MainScreenViewModel
import com.example.feature.presentation.utils.SortType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainScreenBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel by sharedViewModel<MainScreenViewModel>()

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

        observeUi()

        applyBinding()
    }

    private fun applyBinding() = binding.apply {
        rbSortByBirthday.setOnClickListener {
            viewModel.sortType.value = SortType.BY_DATE
        }
        rbSortByAlphabet.setOnClickListener {
            viewModel.sortType.value = SortType.BY_NAME
        }
    }

    private fun observeUi() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.sortType.collect { sort ->
                when (sort) {
                    SortType.BY_NAME -> binding.rbSortByAlphabet.isChecked
                    SortType.BY_DATE -> binding.rbSortByBirthday.isChecked
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BOTTOM_SHEET_DIALOG_TAG = "BottomSheetDialog"
    }
}