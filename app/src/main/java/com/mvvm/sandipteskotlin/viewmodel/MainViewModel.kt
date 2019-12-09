package com.mvvm.sandiptest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mvvm.sandiptest.repository.UserRepository
import com.mvvm.sandiptest.repository.database.entity.User
//Unuse
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository
    private var userList : LiveData<List<User>>
    init {
        userRepository = UserRepository(application)
        userList = userRepository.getUsers()
    }



    fun getUsers() =  userList

    fun deleteUser(user: User) {
        userRepository.deleteUser(user)
    }
}