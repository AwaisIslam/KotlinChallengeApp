package com.ak.kotlinchallengeapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ak.kotlinchallengeapp.model.UsersDataItem
import com.ak.kotlinchallengeapp.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    application: Application
): AndroidViewModel(application) {

    val response = MutableLiveData<List<UsersDataItem>>()

    val userData = MutableLiveData<UsersDataItem>()

    fun getAllApiData() {
        viewModelScope.launch {
            apiRepository.getAllApiData()
                .catch { e ->
                    Log.d("Api Response Exception", "getAllApiData: ${e.message}")
                }
                .collect {
                response.value = it
            }
        }
    }

    fun getApiDataById() {
        viewModelScope.launch {
            apiRepository.getApiDataById()
                .catch { e ->
                    Log.d("Api Response Exception", "getApiDataById: ${e.message}")
                }
                .collect{
                    userData.value = it
                }
        }
    }
}