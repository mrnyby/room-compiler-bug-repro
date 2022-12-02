package com.example.repro.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.repro.User
import com.example.repro.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}