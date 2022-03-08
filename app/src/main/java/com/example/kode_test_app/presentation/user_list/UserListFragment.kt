package com.example.kode_test_app.presentation.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kode_test_app.BaseFragment
import com.example.kode_test_app.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : BaseFragment<FragmentUserListBinding>() {

    private val viewModel: UserListViewModel by viewModels()

    private lateinit var adapter: UserListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserListAdapter()

        viewModel.data.observe(viewLifecycleOwner) { event ->
            event.data?.let {
                adapter.submitList(it)
            }
        }

        binding.rvUserList.adapter = adapter
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUserListBinding.inflate(inflater, container, false)

}