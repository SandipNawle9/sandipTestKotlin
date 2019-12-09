package com.mvvm.sandiptest.repository.network

import com.mvvm.sandiptest.repository.database.entity.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitApiClient{

    @GET("/users")
    fun getUsers() : Call<List<User>>


    companion object{
        @Volatile
        private var instance : RetrofitApiClient? = null
        private val LOCK = Any()

        operator fun invoke() = instance ?: synchronized(LOCK){
            instance ?: createRetrofitAPI().also {
                instance = it
            }
        }

        private fun createRetrofitAPI() =
                Retrofit.Builder()
                        .baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(RetrofitApiClient::class.java)
    }
}