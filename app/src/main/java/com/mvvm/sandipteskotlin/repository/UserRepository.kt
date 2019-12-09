package com.mvvm.sandiptest.repository

import android.content.Context
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.sandipteskotlin.repository.database.UserAppDatabase
import com.mvvm.sandiptest.repository.database.dao.UserDao
import com.mvvm.sandiptest.repository.database.entity.User
import com.mvvm.sandiptest.repository.network.RetrofitApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository(context: Context) {
    private var userDao : UserDao? =null
    val userRespone = MutableLiveData<List<User>>()
    var mSectionLive : MediatorLiveData<List<User>> = MediatorLiveData()
    init {
//        userDao = AppDatabase(context)?.getUserDao()

        //Java fuction call
        userDao =  UserAppDatabase.getUserDatabase(context).getUserDao()
    }

    fun getUsers(): LiveData<List<User>> {


        RetrofitApiClient().getUsers().enqueue(object  : Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
               userRespone.value = userDao!!.getUsers()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                userDao!!.deleteAll();
                userDao!!.insertUsers( response.body()!!)

                userRespone.value = userDao!!.getUsers()

        }
        })
        return userRespone
    }

    fun deleteUser(user: User) {
        userDao!!.deleteUser(user)
    }
}

