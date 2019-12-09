package com.mvvm.sandiptest.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mvvm.sandiptest.repository.UserRepository
import com.mvvm.sandiptest.repository.database.entity.User

class UserViewModel(application: Application) :AndroidViewModel(application) {
    private val userRepository: UserRepository
    private var userList : LiveData<List<User>>
    init {
        userRepository = UserRepository(application.applicationContext)
        userList = userRepository.getUsers()
    }



    fun getUsers() = userRepository.getUsers()

    fun deleteUser(user: User) {
        userRepository.deleteUser(user)
    }



}