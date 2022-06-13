package com.example.feature.presentation.user_list.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.feature.presentation.user_list.fragment.UserListFragment

class UserListViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount() = com.example.feature.domain.model.DepartmentList.departmentListUi.size

    override fun createFragment(position: Int) = UserListFragment.newInstance(position)
}