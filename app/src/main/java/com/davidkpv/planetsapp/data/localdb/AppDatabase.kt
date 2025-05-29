package com.davidkpv.planetsapp.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.davidkpv.planetsapp.data.dao.UserDao
import com.davidkpv.planetsapp.data.entities.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
