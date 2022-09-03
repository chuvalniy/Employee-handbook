package com.example.feature.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature.R
import com.example.feature.databinding.ModelLoadingHeaderBinding

data class HomeLoadingHeaderModel(
    private val header: String
) : ViewBindingKotlinModel<ModelLoadingHeaderBinding>(R.layout.model_loading_header) {

    override fun ModelLoadingHeaderBinding.bind() {
        tvHeader.text = header.toUiDepartment()
    }

}

private fun String.toUiDepartment(): String {
    return when (this) {
        "android" -> "Android"
        "ios" -> "iOS"
        "design" -> "Design"
        "management" -> "Management"
        "qa" -> "QA"
        "hr" -> "HR"
        "pr" -> "PR"
        "backend" -> "Backend"
        "frontend" -> "frontend"
        "back_office" -> "Back ofice"
        "support" -> "Support"
        "analytics" -> "Analytics"
        else -> "All"
    }
}