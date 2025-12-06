package com.example.fitbit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalorieDao {

    @Insert
    suspend fun insert(entry: CalorieEntry)

    @Query("SELECT * FROM calorie_entries ORDER BY timestamp DESC")
    suspend fun getAll(): List<CalorieEntry>
}
