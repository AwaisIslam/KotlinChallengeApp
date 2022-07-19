package com.ak.kotlinchallengeapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ak.kotlinchallengeapp.databinding.UserDetailBinding
import com.ak.kotlinchallengeapp.viewmodel.ApiViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersDetailActivity: AppCompatActivity() {

    private val viewModel by viewModels<ApiViewModel>()
    private lateinit var binding: UserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getApiDataById()
    }

    private fun getApiDataById() {
        viewModel.getApiDataById()
        viewModel.userData.observe(this){
            val userData = it
            binding.textViewTitleDetail.text = userData.title
            binding.textViewBodyDetail.text = userData.body
            binding.progressBarDetail.visibility = View.GONE
        }
    }
}