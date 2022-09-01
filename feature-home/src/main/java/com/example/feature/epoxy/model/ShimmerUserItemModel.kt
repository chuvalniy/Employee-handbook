package com.example.feature.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature.R
import com.example.feature.databinding.ShimmerUserItemBinding

class ShimmerUserItemModel : ViewBindingKotlinModel<ShimmerUserItemBinding>(R.layout.shimmer_user_item) {

    override fun ShimmerUserItemBinding.bind() {
        shimmerLayoutUserItem.startShimmer()
    }

    override fun ShimmerUserItemBinding.unbind() {
        shimmerLayoutUserItem.stopShimmer()
    }
}