package com.example.kode_test_app.presentation.user_main_screen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kode_test_app.domain.model.DepartmentList

class UserListViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount() = DepartmentList.departmentListUi.size

    override fun createFragment(position: Int) = UserListFragment.newInstance(position)
}