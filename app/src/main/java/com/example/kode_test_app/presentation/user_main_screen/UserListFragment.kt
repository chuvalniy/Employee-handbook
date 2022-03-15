package com.example.kode_test_app.presentation.user_main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kode_test_app.core.BaseFragment
import com.example.kode_test_app.core.utils.SortType
import com.example.kode_test_app.databinding.FragmentUserListBinding
import com.example.kode_test_app.presentation.user_list.MainScreenFragmentDirections
import com.example.kode_test_app.presentation.user_list.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class UserListFragment : BaseFragment<FragmentUserListBinding>() {

    private val viewModel: MainScreenViewModel by activityViewModels()

    private lateinit var adapter: UserListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = UserListAdapter(
            onMoveToDetail = { user ->
                val action = MainScreenFragmentDirections
                    .actionUserListToUserDetail(user.id)
                findNavController().navigate(action)
            }
        )

        viewModel.data.observe(viewLifecycleOwner) { users ->
            users?.let {
                adapter.submitList(it)
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshDataFromRepository()
            binding.swipeRefreshLayout.isRefreshing = false
        }
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