package com.example.core_data.repository

import com.example.core_model.DomainUser
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    fun fetchData(id: String): Flow<DomainUser>
}