package com.mvvm.sandiptest.repository.network.retrofitinterface

import com.mvvm.sandiptest.repository.database.entity.User
import retrofit2.Call
import retrofit2.http.GET

interface UserRetofitInterface {

    @GET("/users")
    fun getUsers() :  Call<List<User>>


}