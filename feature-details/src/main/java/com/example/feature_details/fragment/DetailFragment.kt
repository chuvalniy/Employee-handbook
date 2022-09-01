package com.example.feature_details.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core.presentation.BaseFragment
import com.example.feature_details.databinding.FragmentDetailBinding
import com.example.feature_details.di.DetailComponentViewModel
import com.example.feature_details.model.DetailEvent
import com.example.feature_details.model.DetailSideEffect
import com.example.feature_details.model.DetailState
import com.example.feature_details.view_model.AssistedDetailsViewModelFactory
import com.example.feature_details.view_model.DetailViewModel
import javax.inject.Inject

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    @Inject
    internal lateinit var factory: AssistedDetailsViewModelFactory

    private val viewModel: DetailViewModel by viewModels {
        factory.create(this, defaultArgs = arguments)
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<DetailComponentViewModel>()
            .detailsComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUiState()
        observeUiEffect()

        processUiEvent()
    }

    private fun observeUiEffect() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is DetailSideEffect.NavigateBack -> findNavController().popBackStack()
            }
        }
    }

    private fun processUiEvent() {
        binding.btnGoBack.setOnClickListener {
            viewModel.onEvent(DetailEvent.BackButtonPressed)
        }
    }

    private fun observeUiState() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.state.collect { state ->
            processUiState(state)
        }
    }

    private fun processUiState(state: DetailState) {
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