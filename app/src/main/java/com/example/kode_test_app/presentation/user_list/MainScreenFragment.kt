package com.example.kode_test_app.presentation.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.kode_test_app.BaseFragment
import com.example.kode_test_app.databinding.FragmentMainScreenBinding
import com.example.kode_test_app.databinding.FragmentUserListBinding
import com.example.kode_test_app.presentation.user_main_screen.UserListAdapter
import com.example.kode_test_app.utils.SortType
import com.example.kode_test_app.utils.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>() {

    // In MainScreenFragment viewModel is used to set a value to sort type and search query.
    private val viewModel: MainScreenViewModel by activityViewModels()

    private lateinit var adapter: MainScreenViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MainScreenViewPagerAdapter(this)

        binding.apply {
            iconSort.setOnClickListener {
                showUserListDialogFragment()
            }
            searchView.onQueryTextChanged { query ->
                viewModel.queryText.value = query
            }
        }

        binding.vpMainScreen.adapter = adapter

        setupUserListDialogFragment()
    }

    private fun showUserListDialogFragment() {
        MainScreenDialogFragment.show(parentFragmentManager)
    }

    private fun setupUserListDialogFragment() {
        MainScreenDialogFragment.setupListener(parentFragmentManager, viewLifecycleOwner) {
            when (it) {
                0 -> viewModel.sortType.value = SortType.BY_NAME
                1 -> viewModel.sortType.value = SortType.BY_DATE
            }
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainScreenBinding.inflate(inflater, container, false)
}