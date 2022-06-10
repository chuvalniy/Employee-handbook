package com.example.feature.presentation.user_main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.core.ui.BaseFragment
import com.example.core.ui.onQueryTextChanged
import com.example.feature.databinding.FragmentMainScreenBinding
import com.example.feature.domain.model.DepartmentList
import com.example.feature.presentation.user_main.view_model.MainScreenViewModel
import com.example.feature.presentation.user_main.user_list.adapter.UserListViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>() {

    private val viewModel by sharedViewModel<MainScreenViewModel>()

    private lateinit var viewPager: UserListViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPagerWithTabLayout()

        handleSearchView()

        applyBinding()
    }

    private fun handleSearchView() {
        binding.apply {
            searchView.onQueryTextChanged { query ->
                viewModel.queryText.value = query
            }

            searchView.setOnQueryTextFocusChangeListener { view, hasFocus ->
                binding.apply {
                    iconSort.isVisible = !hasFocus
                    btnCancel.isVisible = hasFocus
                    btnCancel.setOnClickListener {
                        view.clearFocus()
                    }
                }
            }
        }
    }

    private fun applyBinding() = binding.apply {
        iconSort.setOnClickListener {
            MainScreenBottomSheetFragment().show(
                parentFragmentManager,
                MainScreenBottomSheetFragment.BOTTOM_SHEET_DIALOG_TAG
            )
        }
    }

    private fun setupViewPagerWithTabLayout() {
        viewPager = UserListViewPagerAdapter(this)

        binding.vpMainScreen.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(page: Int) {
                viewModel.filterType.value =
                    DepartmentList.departmentListDatabase[page]
            }
        })

        binding.vpMainScreen.adapter = viewPager

        TabLayoutMediator(binding.tabLayout, binding.vpMainScreen) { tab, position ->
            tab.text = DepartmentList.departmentListUi[position]
        }.attach()
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainScreenBinding.inflate(inflater, container, false)
}