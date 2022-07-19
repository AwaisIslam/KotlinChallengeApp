package com.ak.kotlinchallengeapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ak.kotlinchallengeapp.adapter.UserListAdapter
import com.ak.kotlinchallengeapp.databinding.ActivityMainBinding
import com.ak.kotlinchallengeapp.interfaces.UserListAdapterListener
import com.ak.kotlinchallengeapp.model.UsersDataItem
import com.ak.kotlinchallengeapp.viewmodel.ApiViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UserListAdapterListener{

    private val viewModel by viewModels<ApiViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var userListAdapter: UserListAdapter
    private lateinit var userList: List<UsersDataItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAllApiData()
    }

    private fun updateControls(userData: List<UsersDataItem>) {
        binding.recyclerViewUserList.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewUserList.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerViewUserList.context,
            layoutManager.orientation
        )
        binding.recyclerViewUserList.addItemDecoration(dividerItemDecoration)
        userListAdapter = UserListAdapter(userData,this)
        binding.recyclerViewUserList.adapter = userListAdapter
    }

    private fun getAllApiData() {
        viewModel.getAllApiData()
        viewModel.response.observe(this) {
            userList = it
            updateControls(userList)
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onShowUserDetail() {
        val intent = Intent(this,UsersDetailActivity::class.java)
        startActivity(intent)
    }
}