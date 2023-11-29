package com.example.coino.feature_search.data.repositories

import android.util.Log
import com.example.coino.core.util.Resource
import com.example.coino.feature_search.data.repositories.remote.SearchCoinApi
import com.example.coino.feature_search.data.repositories.remote.DTOs.toSearchCoins
import com.example.coino.feature_search.domain.SearchCoinsRepository
import com.example.coino.feature_search.domain.model.SearchCoins
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchCoinsRepositoryImpl @Inject constructor(
    private val api: SearchCoinApi
) : SearchCoinsRepository {
    override fun searchCoins(query: String): Flow<Resource<List<SearchCoins>>> = flow {
        try {
            emit(Resource.Loading())
            val searchResult = api.searchCoins(
                query = query
            ).coins.map {
                it.toSearchCoins()
            }
            emit(Resource.Success(searchResult))
            Log.d("SearchResult", "searchCoins: $searchResult")
        } catch (e: HttpException) {
            emit(Resource.Error("An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}