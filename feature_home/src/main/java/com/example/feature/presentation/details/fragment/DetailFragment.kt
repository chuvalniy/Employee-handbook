package com.example.feature.presentation.details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.ui.BaseFragment
import com.example.core.utils.ConvertType
import com.example.core.utils.convertFromTimestampIntoDate
import com.example.feature.R
import com.example.feature.databinding.FragmentDetailBinding
import com.example.feature.presentation.details.model.DetailsState
import com.example.feature.presentation.details.model.UiEvent
import com.example.feature.presentation.details.model.UiSideEffect
import com.example.feature.presentation.details.view_model.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val viewModel by stateViewModel<DetailViewModel>(state = {
        val bundle = Bundle()
        bundle.putParcelable(KEY_USER, arguments?.getParcelable(KEY_USER))
        bundle
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUiState()
        observeUiEffect()

        processUiEvent()
    }
    private fun observeUiEffect() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEffect.collect { effect ->
                when (effect) {
                    is UiSideEffect.NavigateBack -> {
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun processUiEvent() {
        binding.btnGoBack.setOnClickListener {
            viewModel.onEvent(UiEvent.BackButtonPressed)
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                processUiState(state)
            }
        }
    }

    private fun processUiState(state: DetailsState) {
        with(binding) {
            tvUserName.text = state.data.name
            tvAge.text = getString(
                R.string.text_view_age, convertFromTimestampIntoDate(
                    state.data.timestamp, ConvertType.NUMBER_OF_YEARS
                )
            )
            tvBirthday.text = convertFromTimestampIntoDate(
                state.data.timestamp,
                ConvertType.FULL_DATE
            )
            tvPhoneNumber.text = state.data.phone
            tvUserTag.text = state.data.userTag
            tvPosition.text = state.data.position
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailBinding.inflate(inflater, container, false)

    companion object {
        const val KEY_USER = "user"
    }
}