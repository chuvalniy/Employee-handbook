package com.example.feature.presentation.home.epoxy

import android.util.Log
import com.airbnb.epoxy.TypedEpoxyController
import com.example.feature.domain.model.DomainDataSource
import com.example.feature.presentation.home.epoxy.model.HomeSearchModel
import com.example.feature.presentation.home.epoxy.model.HomeTabLayoutModel
import com.example.feature.presentation.home.epoxy.model.HomeUserItemModel
import com.example.feature.presentation.home.epoxy.model.ShimmerUserItemModel
import com.example.feature.presentation.home.view_model.UiState

class HomeEpoxyController(
    private val onUserSearch: (String) -> Unit,
    private val onMoveToDetail: (DomainDataSource) -> Unit,
    private val onSelectDepartment: (String) -> Unit
) : TypedEpoxyController<UiState>() {

    override fun buildModels(state: UiState?) {

        Log.d("TAGTAG", "${state?.isLoading}, ${state?.departmentFilter}, ${state?.searchQuery}, ${state?.sortType}")

        HomeSearchModel(onUserSearch)
            .id("home_search")
            .addTo(this)

        HomeTabLayoutModel(onSelectDepartment)
            .id("home_department_tab_layout")
            .addTo(this)

        if (state?.isLoading == true) {
            repeat(8) {
                ShimmerUserItemModel()
                    .id("shimmer_user_item_$it")
                    .addTo(this)
            }
        } else if (state?.isLoading == false) {
            state.data.forEach { item ->
                HomeUserItemModel(item, onMoveToDetail)
                    .id("user_${item.id}")
                    .addTo(this)
            }

        }
    }
}