package com.ak.kotlinchallengeapp.repository

import com.ak.kotlinchallengeapp.interfaces.ApiInterface
import com.ak.kotlinchallengeapp.model.UsersDataItem
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class ApiRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    fun getAllApiData(): Flow<List<UsersDataItem>> = flow{
        val usersList = apiInterface.getAllApiData()
        emit(usersList)
    }.flowOn(Dispatchers.IO)

    suspend fun getApiDataById(): Flow<UsersDataItem> = flow {
        val userData = apiInterface.getApiDataById()
        emit(userData)
    }.flowOn(Dispatchers.IO)
}