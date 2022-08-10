package com.example.feature.presentation.home.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature.R
import com.example.feature.databinding.ModelUserItemBinding
import com.example.feature.domain.model.DomainDataSource

data class HomeUserItemModel(
    private val user: DomainDataSource
) : ViewBindingKotlinModel<ModelUserItemBinding>(R.layout.model_user_item) {

    override fun ModelUserItemBinding.bind() {
        
    }
}