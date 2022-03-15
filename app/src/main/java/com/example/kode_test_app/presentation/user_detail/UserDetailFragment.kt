package com.example.kode_test_app.presentation.user_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kode_test_app.core.BaseFragment
import com.example.kode_test_app.core.utils.ConvertType
import com.example.kode_test_app.core.utils.ImageLoader.loadImage
import com.example.kode_test_app.core.utils.convertFromTimestampIntoDate
import com.example.kode_test_app.databinding.FragmentUserDetailBinding
import com.example.kode_test_app.domain.model.DepartmentList
import com.example.kode_test_app.domain.model.User
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>() {

    private val navigationArgs: UserDetailFragmentArgs by navArgs()

    private val viewModel: UserDetailViewModel by viewModels()

    private lateinit var user: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.id

        viewModel.getUser(id).observe(viewLifecycleOwner) { selectedUser ->
            user = selectedUser
            setUser(user)
        }
    }

    private fun setUser(user: User) {
        binding.apply {
            tvUserName.text = user.name
            tvUserTag.text = user.userTag
            tvPosition.text = user.position
            tvPhoneNumber.text = user.phone
            loadImage(user.avatarUrl, ivAvatar)
            tvAge.text = "${convertFromTimestampIntoDate(user.timestamp, ConvertType.NUMBER_OF_YEARS)} years"
            tvBirthday.text = convertFromTimestampIntoDate(user.timestamp, ConvertType.FULL_DATE)
            ivArrowBack.setOnClickListener { findNavController().navigateUp() }
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUserDetailBinding.inflate(inflater, container, false)

}