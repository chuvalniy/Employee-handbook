package com.example.core_data.repository

import com.example.core_model.model.DomainUser
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    fun fetchData(id: String): Flow<DomainUser>
}