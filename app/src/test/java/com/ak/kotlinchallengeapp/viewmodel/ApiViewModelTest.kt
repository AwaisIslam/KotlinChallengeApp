package com.ak.kotlinchallengeapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.ak.kotlinchallengeapp.repository.ApiRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ApiViewModelTest{

    private lateinit var viewModel: ApiViewModel
    private lateinit var repository: ApiRepository

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val application = Mockito.mock(Application::class.java)

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        viewModel = ApiViewModel(repository,application)
    }

    @Test
    fun getAllApiData() {
        viewModel.getAllApiData()
        assert(viewModel.response.value?.size != null)
    }
}