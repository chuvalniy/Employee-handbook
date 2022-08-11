package com.example.feature.presentation.home.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature.R
import com.example.feature.databinding.ModelTabLayoutBinding
import com.example.feature.domain.model.DepartmentList
import com.google.android.material.tabs.TabLayout

data class HomeTabLayoutModel(
    private val onSelectDepartment: (String) -> Unit
) : ViewBindingKotlinModel<ModelTabLayoutBinding>(R.layout.model_tab_layout) {

    override fun ModelTabLayoutBinding.bind() {
        tabLayout.removeAllTabs()

        DepartmentList.departmentListUi.onEach {
            tabLayout.addTab(tabLayout.newTab().setText(it))
        }

        // TODO: replace with extension
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    onSelectDepartment(DepartmentList.departmentListDatabase[tab.position])
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}