package com.muflihunnisa.githubfirstsubmission

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muflihunnisa.githubfirstsubmission.data.model.Users
import com.muflihunnisa.githubfirstsubmission.databinding.ItemUserBinding

class UserAdapter (private var listUsers: ArrayList<Users>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private lateinit var onItemClickCallback : OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback : OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(users: Users)
    }

    class UserViewHolder(var userBinding: ItemUserBinding) : RecyclerView.ViewHolder(userBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(userBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val users = listUsers[position]
        Glide.with(holder.itemView.context).load(users.avatar).into(holder.userBinding.ivItemUser)
        holder.userBinding.tvNameItem.text = users.name
        holder.userBinding.tvUsernameItem.text = users.username
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUsers[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listUsers.size
}