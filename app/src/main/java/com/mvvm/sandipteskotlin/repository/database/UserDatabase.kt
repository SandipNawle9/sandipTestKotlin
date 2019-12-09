package com.mvvm.sandiptest.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvvm.sandiptest.repository.database.dao.UserDao
import com.mvvm.sandiptest.repository.database.entity.User

@Database(
        entities = [User::class],
        version = 1
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao() : UserDao
    companion object {

        @Volatile
        private var instance: UserDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildUserDatabase(context).also {
            instance = it }
        }

        private fun buildUserDatabase(context: Context):UserDatabase {
            return Room.databaseBuilder(context, UserDatabase::class.java, "usertest.db").build()
        }
    }

//    companion object {
//        var INSTANCE: UserDatabase? = null
//
//        fun getAppDataBase(context: Context): UserDatabase? {
//            if (INSTANCE == null){
//                synchronized(UserDatabase::class){
//                    INSTANCE = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "myDB").build()
//                }
//            }
//            return INSTANCE
//        }
//
//        fun destroyDataBase(){
//            INSTANCE = null
//        }
//    }

}