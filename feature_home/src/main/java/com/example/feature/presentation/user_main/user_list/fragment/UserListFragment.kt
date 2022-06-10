package com.example.feature.presentation.user_main.user_list.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.core.ui.BaseFragment
import com.example.core.utils.Resource
import com.example.feature.R
import com.example.feature.databinding.FragmentUserListBinding
import com.example.feature.presentation.user_detail.fragment.UserDetailFragment
import com.example.feature.presentation.user_main.user_list.adapter.UserListAdapter
import com.example.feature.presentation.user_main.view_model.MainScreenViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserListFragment : BaseFragment<FragmentUserListBinding>() {

    private val viewModel by sharedViewModel<MainScreenViewModel>()

    private var adapter: UserListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observeUi()
    }

    private fun observeUi() {
        viewModel.data.observe(viewLifecycleOwner) { event ->
            when (event) {
                is Resource.Success -> {
                    event.data?.let {
                        adapter?.submitList(it)
                    }
                    binding.rvUserList.isVisible = true
                    binding.progressBar.isVisible = false
                    binding.linearLayoutError.isVisible = false
                }
                is Resource.Loading -> {
                    binding.rvUserList.isVisible = false
                    binding.progressBar.isVisible = true
                    binding.linearLayoutError.isVisible = false

                }
                is Resource.Error -> {
                    binding.rvUserList.isVisible = false
                    binding.progressBar.isVisible = false
                    binding.linearLayoutError.isVisible = true
                    binding.tvErrorBody.text = event.error ?: getString(R.string.unexpected_error_occurred)
                }
            }
        }
    }

    private fun setupAdapter() {
        adapter = UserListAdapter(
            onMoveToDetail = { user ->
                findNavController().navigate(
                    Uri.parse("${UserDetailFragment.DEEP_LINK_NAVIGATION}/${user.id}")
                )
            }
        )

        binding.rvUserList.adapter = adapter
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUserListBinding.inflate(inflater, container, false)

    companion object {
        var DEP_ARG = "dep_arg"

        @JvmStatic
        fun newInstance(departmentId: Int) = UserListFragment().apply {
            arguments = Bundle().apply {
                putInt(DEP_ARG, departmentId)
            }
        }
    }
}