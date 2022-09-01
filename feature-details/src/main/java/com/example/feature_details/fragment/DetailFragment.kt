package com.example.feature_details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.presentation.BaseFragment
import com.example.feature_details.databinding.FragmentDetailBinding
import com.example.feature_details.model.DetailsEvent
import com.example.feature_details.model.DetailsSideEffect
import com.example.feature_details.model.DetailsState
import com.example.feature_details.view_model.DetailViewModel
import com.example.feature_details.view_model.DetailViewModelFactory
import javax.inject.Inject

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    @Inject
    internal lateinit var factory: Lazy<DetailViewModelFactory>

    private val viewModel: DetailViewModel by viewModels { factory.value }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUiState()
        observeUiEffect()

        processUiEvent()
    }

    private fun observeUiEffect() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is DetailsSideEffect.NavigateBack -> findNavController().popBackStack()
            }
        }
    }

    private fun processUiEvent() {
        binding.btnGoBack.setOnClickListener {
            viewModel.onEvent(DetailsEvent.BackButtonPressed)
        }
    }

    private fun observeUiState() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.state.collect { state ->
            processUiState(state)
        }
    }

    private fun processUiState(state: DetailsState) {
        with(binding) {
            state.data?.let {
                tvUserName.text = state.data.name
                tvAge.text = getString(com.example.core.R.string.age, state.data.age)
                tvBirthday.text = state.data.birthdayFull
                tvPhoneNumber.text = state.data.phone
                tvUserTag.text = state.data.userTag
                tvPosition.text = state.data.position
            }
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailBinding.inflate(inflater, container, false)
}