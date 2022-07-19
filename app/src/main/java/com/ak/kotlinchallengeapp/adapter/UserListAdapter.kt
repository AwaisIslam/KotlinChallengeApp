package com.ak.kotlinchallengeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ak.kotlinchallengeapp.databinding.UsersListBinding
import com.ak.kotlinchallengeapp.interfaces.UserListAdapterListener
import com.ak.kotlinchallengeapp.model.UsersDataItem

class UserListAdapter(
    private var userList: List<UsersDataItem>,
    private val listAdapterListener: UserListAdapterListener
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    inner class ViewHolder(
        dataBinding: UsersListBinding,
        private val listAdapterListener: UserListAdapterListener
    ) : RecyclerView.ViewHolder(dataBinding.root) {

        val textViewId = dataBinding.textViewId
        val textViewTitle = dataBinding.textViewTitle
        val textViewBody = dataBinding.textViewBody
        val cardView = dataBinding.userItem

        init {
            dataBinding.userItem.setOnClickListener {
                listAdapterListener.onShowUserDetail()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UsersListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            listAdapterListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.textViewId.text = user.id.toString()
        holder.textViewTitle.text = user.title
        holder.textViewBody.text = user.body
    }

    override fun getItemCount() = userList.size
}