package com.example.coino.feature_coins.data.repositories.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CoinsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CoinsDatabase : RoomDatabase() {
    abstract val dao: CoinsDao

}