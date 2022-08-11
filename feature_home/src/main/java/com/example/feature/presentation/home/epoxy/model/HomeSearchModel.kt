package com.example.feature.presentation.home.epoxy.model

import androidx.core.view.isVisible
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.core.ui.onQueryTextChanged
import com.example.feature.R
import com.example.feature.databinding.ModelSearchBinding

data class HomeSearchModel(
    private val onUserSearch: (String) -> Unit
) : ViewBindingKotlinModel<ModelSearchBinding>(R.layout.model_search) {

    override fun ModelSearchBinding.bind() {
        searchView.onQueryTextChanged(onUserSearch)
        searchView.setOnQueryTextFocusChangeListener { view, hasFocus ->
            iconSort.isVisible = !hasFocus
            btnCancel.isVisible = hasFocus
            btnCancel.setOnClickListener {
                view.clearFocus()
                searchView.setQuery("", false)
            }
        }

    }
}