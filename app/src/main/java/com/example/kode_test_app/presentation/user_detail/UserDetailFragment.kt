package com.example.kode_test_app.presentation.user_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.kode_test_app.core.BaseFragment
import com.example.kode_test_app.databinding.FragmentUserDetailBinding
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
            tvUserName.text = user.firstName
            tvUserSurname.text = user.lastName
            tvUserTag.text = user.userTag.lowercase()
            tvPosition.text = user.position
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUserDetailBinding.inflate(inflater, container, false)

}