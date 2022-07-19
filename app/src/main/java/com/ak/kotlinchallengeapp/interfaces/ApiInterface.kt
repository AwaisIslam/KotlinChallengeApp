package com.ak.kotlinchallengeapp.interfaces

import com.ak.kotlinchallengeapp.model.UsersDataItem
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts/")
    suspend fun getAllApiData(): List<UsersDataItem>

    @GET("posts/1")
    suspend fun getApiDataById(): UsersDataItem
}