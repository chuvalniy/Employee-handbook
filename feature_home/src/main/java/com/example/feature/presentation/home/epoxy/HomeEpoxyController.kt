package com.example.feature.presentation.home.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.example.feature.domain.model.DomainDataSource
import com.example.feature.presentation.home.epoxy.model.*
import com.example.feature.presentation.home.view_model.SortType
import com.example.feature.presentation.home.model.UiState

class HomeEpoxyController(
    private val onMoveToDetail: (DomainDataSource) -> Unit,
) : TypedEpoxyController<UiState>() {

    override fun buildModels(state: UiState?) {
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

            if (state.sortType == SortType.BY_DATE) {
                state.data.forEach { item ->
                    HomeUserItemBirthdayModel(item, onMoveToDetail)
                        .id("user_${item.id}")
                        .addTo(this)
                }
            } else if (state.sortType == SortType.BY_NAME) {
                state.data.forEach { item ->
                    HomeUserItemAlphabetModel(item, onMoveToDetail)
                        .id("user_${item.id}")
                        .addTo(this)
                }
            }


        }
    }
}