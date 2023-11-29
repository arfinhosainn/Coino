package com.example.coino.feature_coins.data.repositories.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CoinsDao {

    @Upsert
    suspend fun upsertAll(coins: List<CoinsEntity>)

    @Query("SELECT * FROM coinsentity")
    fun pagingSource(): PagingSource<Int, CoinsEntity>

    @Query("DELETE FROM coinsentity")
    suspend fun clearAll()


}