package com.example.coino.feature_search.domain

import com.example.coino.core.util.Resource
import com.example.coino.feature_search.domain.model.SearchCoins
import kotlinx.coroutines.flow.Flow

interface SearchCoinsRepository {

    fun searchCoins(
        query: String
    ): Flow<Resource<List<SearchCoins>>>


}