package com.example.feature.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.example.core_model.model.DomainUser
import com.example.feature.epoxy.model.HomeLoadingHeaderModel
import com.example.feature.epoxy.model.HomeUserItem
import com.example.feature.epoxy.model.ShimmerUserItemModel
import com.example.feature.model.HomeState
import com.example.feature.model.LoadingState

class HomeEpoxyController(
    private val onMoveToDetail: (DomainUser) -> Unit,
) : TypedEpoxyController<HomeState>() {

    override fun buildModels(state: HomeState?) {
        if (state?.loadingState == LoadingState.SHIMMER) {
            HomeLoadingHeaderModel(state.departmentFilter)
                .id("shimmer_loading_header")
                .addTo(this)

            repeat(8) {
                ShimmerUserItemModel()
                    .id("shimmer_user_item_$it")
                    .addTo(this)
            }
        } else if (state?.loadingState == LoadingState.NONE || state?.loadingState == LoadingState.SNACKBAR) {
            state.data.forEach { item ->
                HomeUserItem(item, state.sortType, onMoveToDetail)
                    .id("user_${item.id}")
                    .addTo(this)
            }
        }
    }
}