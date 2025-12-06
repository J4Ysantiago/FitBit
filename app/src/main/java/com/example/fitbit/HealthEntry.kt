package com.example.fitbit

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calorie_entries")
data class CalorieEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val foodName: String,
    val calories: Int,
    val timestamp: Long = System.currentTimeMillis()
)
