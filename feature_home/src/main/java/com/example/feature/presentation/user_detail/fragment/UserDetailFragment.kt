package com.example.feature.presentation.user_detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.core.ui.BaseFragment
import com.example.core.utils.ConvertType
import com.example.core.utils.convertFromTimestampIntoDate
import com.example.feature.R
import com.example.feature.databinding.FragmentUserDetailBinding
import com.example.feature.domain.model.User
import com.example.feature.presentation.user_detail.view_model.UserDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>() {

    private val viewModel by viewModel<UserDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val argument = arguments?.getString("id")
        viewModel.getUser(argument ?: "0").observe(viewLifecycleOwner) { user ->
            setUser(user)
        }
    }

    private fun setUser(user: User) {
        binding.apply {
            tvUserName.text = user.name
            tvUserTag.text = user.userTag
            tvPosition.text = user.position
            tvPhoneNumber.text = user.phone
            tvBirthday.text = convertFromTimestampIntoDate(
                user.timestamp,
                ConvertType.FULL_DATE
            )
            tvAge.text = getString(R.string.text_view_age, convertFromTimestampIntoDate(
                user.timestamp, ConvertType.NUMBER_OF_YEARS
            ))
            ivArrowBack.setOnClickListener { findNavController().navigateUp() }
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUserDetailBinding.inflate(inflater, container, false)

    companion object {
        const val DEEP_LINK_NAVIGATION = "myApp://featureUserList"
    }
}