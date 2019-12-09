package com.mvvm.sandiptest.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mvvm.sandiptest.repository.database.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(userList: List<User>)

    @Query("Select * from user")
    fun getUsers(): List<User>

    @Delete
    fun deleteUser(user : User) : Int

    @Query("Delete from user")
    fun deleteAll()
}