package com.example.feature.epoxy.model

import androidx.core.view.isVisible
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.core.utils.SortType
import com.example.core_model.DomainUser
import com.example.feature.R
import com.example.feature.databinding.ModelUserItemBinding

data class HomeUserItem(
    private val user: DomainUser,
    private val sortType: SortType,
    private val onMoveToDetail: (DomainUser) -> Unit,
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