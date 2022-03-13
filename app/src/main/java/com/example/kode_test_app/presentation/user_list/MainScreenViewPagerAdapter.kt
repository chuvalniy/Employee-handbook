package com.example.kode_test_app.presentation.user_list

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kode_test_app.presentation.user_main_screen.UserListFragment

class MainScreenViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int) = UserListFragment.newInstance(position)
}