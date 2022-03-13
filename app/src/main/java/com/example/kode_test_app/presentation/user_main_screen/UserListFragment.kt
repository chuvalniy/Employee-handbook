package com.example.kode_test_app.presentation.user_main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.kode_test_app.BaseFragment
import com.example.kode_test_app.databinding.FragmentUserListBinding
import com.example.kode_test_app.presentation.user_list.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : BaseFragment<FragmentUserListBinding>() {

    // In UserListFragment viewModel is used to observe data and set filter type.
    private val viewModel: MainScreenViewModel by activityViewModels()

    private lateinit var adapter: UserListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserListAdapter()

        viewModel.data.observe(viewLifecycleOwner) { users ->
            users?.let {
                adapter.submitList(it)
            }
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