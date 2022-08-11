package com.example.feature.presentation.home.epoxy

import android.util.Log
import com.airbnb.epoxy.TypedEpoxyController
import com.example.feature.domain.model.DomainDataSource
import com.example.feature.presentation.home.epoxy.model.*
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
            HomeLoadingHeaderModel()
                .id("shimmer_loading_header")
                .addTo(this)

            repeat(8) {
                ShimmerUserItemModel()
                    .id("shimmer_user_item_$it")
                    .addTo(this)
            }
        } else if (state?.isLoading == false) {
            if (state.data.isEmpty() && state.searchQuery.isNotEmpty()) {
                HomeSearchErrorModel()
                    .id("home_search_error")
                    .addTo(this)
            }

            state.data.forEach { item ->
                HomeUserItemModel(item, onMoveToDetail)
                    .id("user_${item.id}")
                    .addTo(this)
            }

        }
    }
}