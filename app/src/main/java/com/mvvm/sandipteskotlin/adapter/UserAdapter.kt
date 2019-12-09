package com.mvvm.sandiptest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.sandipteskotlin.MainActivity
import com.mvvm.sandipteskotlin.R
import com.mvvm.sandipteskotlin.databinding.UserListItemBinding
import com.mvvm.sandiptest.repository.database.entity.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder>() {
    private var userList: List<User>? = null
    private var deleteUserClick: DeleteUserClick? = null

    interface DeleteUserClick {
        fun onDeleteUserClick(user: User);
    }


    class UserAdapterViewHolder(itemView: UserListItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        var userListItemBinding = itemView
    }

    fun setUserList(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapterViewHolder {
        val userListItemBinding: UserListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_list_item, parent, false
        )
        return UserAdapterViewHolder(userListItemBinding)
    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    override fun onBindViewHolder(holder: UserAdapterViewHolder, position: Int) {
        val user: User = userList!!.get(position)
        holder.userListItemBinding.user = user

        holder.itemView.setOnLongClickListener {
            deleteUserClick?.onDeleteUserClick(user)
            return@setOnLongClickListener true
        }
    }

    fun setClickListner(mainActivity: MainActivity) {
        deleteUserClick = mainActivity
    }


}