package com.example.testapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDao {
    @Update
    suspend fun update(item: Item)

    @Insert
    suspend fun insert(item: Item)

    @Query("SELECT * from item_database")
    fun getItems(): List<Item>

    @Query("DELETE from item_database")
     suspend fun deleteAll()
}