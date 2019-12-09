package com.mvvm.sandipteskotlin.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvvm.sandiptest.repository.database.dao.UserDao
import com.mvvm.sandiptest.repository.database.entity.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object{
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildUserDatabase(context).also {
                instance = it }
        }

        private fun buildUserDatabase(context: Context):AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "usertest.db").build()
        }
    }
}