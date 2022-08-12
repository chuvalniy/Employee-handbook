package com.example.feature.presentation.home.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.core.utils.ConvertType
import com.example.core.utils.convertFromTimestampIntoDate
import com.example.feature.R
import com.example.feature.databinding.ModelUserItemBinding
import com.example.feature.domain.model.DomainDataSource

data class HomeUserItemBirthdayModel(
    private val user: DomainDataSource,
    private val onMoveToDetail: (DomainDataSource) -> Unit,
): ViewBindingKotlinModel<ModelUserItemBinding>(R.layout.model_user_item) {

    override fun ModelUserItemBinding.bind() {
        tvUserName.text = user.name
        tvUserTag.text = user.userTag
        tvDepartment.text = user.department
        tvBirthday.text = convertFromTimestampIntoDate(
            user.timestamp,
            ConvertType.DAY_OF_BIRTH
        )
        cvUserItem.setOnClickListener {
            onMoveToDetail(user)
        }
    }
}