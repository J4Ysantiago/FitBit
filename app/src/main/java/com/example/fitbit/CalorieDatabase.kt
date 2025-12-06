package com.example.fitbit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CalorieEntry::class],
    version = 1
)
abstract class CalorieDatabase : RoomDatabase() {

    abstract fun calorieDao(): CalorieDao

    companion object {
        @Volatile
        private var INSTANCE: CalorieDatabase? = null

        fun getDatabase(context: Context): CalorieDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CalorieDatabase::class.java,
                    "calorie_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
