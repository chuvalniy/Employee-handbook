package com.example.feature.presentation.home.epoxy.model

import androidx.core.view.isVisible
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature.R
import com.example.feature.databinding.ModelUserItemBinding
import com.example.feature.domain.model.DomainDataSource
import com.example.feature.presentation.home.model.SortType

data class HomeUserItem(
    private val user: DomainDataSource,
    private val sortType: SortType,
    private val onMoveToDetail: (DomainDataSource) -> Unit,
) : ViewBindingKotlinModel<ModelUserItemBinding>(R.layout.model_user_item) {

    override fun ModelUserItemBinding.bind() {
        tvUserName.text = user.name
        tvUserTag.text = user.userTag
        tvDepartment.text = user.department

        tvBirthday.text = user.birthdayDay
        tvBirthday.isVisible = sortType == SortType.BY_DATE

        cvUserItem.setOnClickListener {
            onMoveToDetail(user)
        }
    }
}