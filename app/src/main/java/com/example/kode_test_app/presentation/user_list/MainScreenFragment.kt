package com.example.kode_test_app.presentation.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.kode_test_app.core.BaseFragment
import com.example.kode_test_app.databinding.FragmentMainScreenBinding
import com.example.kode_test_app.domain.model.DepartmentList
import com.example.kode_test_app.presentation.user_main_screen.UserListViewPagerAdapter
import com.example.kode_test_app.core.utils.SortType
import com.example.kode_test_app.core.utils.onQueryTextChanged
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>() {

    private val viewModel: MainScreenViewModel by activityViewModels()

    private lateinit var viewPager: UserListViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = UserListViewPagerAdapter(this)

        binding.apply {
            iconSort.setOnClickListener {
                showUserListDialogFragment()
            }
            searchView.onQueryTextChanged { query ->
                viewModel.queryText.value = query
            }
        }

        binding.vpMainScreen.adapter = viewPager

        TabLayoutMediator(binding.tabLayout, binding.vpMainScreen) { tab, position ->
            tab.text = DepartmentList.departmentListUi[position]
        }.attach()

        binding.vpMainScreen.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(page: Int) {
                viewModel.filterType.value = DepartmentList.departmentListDatabase[page]
            }
        })

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