package com.example.testapp.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Update
    suspend fun update(item: Item)

    @Query("SELECT * from item_database")
    fun getItems(): Flow<List<Item>>
}