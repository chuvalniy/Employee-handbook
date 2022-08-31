package com.example.feature.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature.R
import com.example.feature.databinding.ModelLoadingHeaderBinding

data class HomeLoadingHeaderModel(
    private val header: String
) : ViewBindingKotlinModel<ModelLoadingHeaderBinding>(R.layout.model_loading_header) {

    override fun ModelLoadingHeaderBinding.bind() {
//        tvHeader.text = header.toUiDepartment() // TODO
        tvHeader.text = "All"
    }

}