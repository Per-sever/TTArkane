package com.example.ttarkane.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ttarkane.data.models.RepositoryEntity
import com.example.ttarkane.data.models.UserEntity
import com.example.ttarkane.databinding.RepositoryItemBinding
import com.example.ttarkane.databinding.UserItemBinding

class GitHubRepoUserAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataUser = mutableListOf<UserEntity>()
    private val data = mutableListOf<Any>()

    var onUserClickListener: ((UserEntity) -> Unit)? = null


    fun addDataUser(data: List<UserEntity>) {
        val userOldCount = dataUser.count()
        dataUser.addAll(data)
        this.data.addAll(userOldCount, data)
        notifyItemRangeChanged(userOldCount, this.data.size - userOldCount)
    }

    fun addDataRepo(data: List<RepositoryEntity>) {
        val oldCount = data.count()
        this.data.addAll(data)
        notifyItemRangeInserted(oldCount, data.size)
    }

    fun clearList() {
        if (data.isNotEmpty()) {
            dataUser.clear()
            data.clear()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_REPOSITORY -> {
                val binding = RepositoryItemBinding.inflate(layoutInflater, parent, false)
                RepositoryViewHolder(binding)
            }

            else -> {
                val binding = UserItemBinding.inflate(layoutInflater, parent, false)
                UserViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> {
                holder.bind(data[position] as UserEntity)
                holder.itemView.setOnClickListener {
                    onUserClickListener?.invoke(data[position] as UserEntity)
                }

            }

            is RepositoryViewHolder -> {
                holder.bind(data[position] as RepositoryEntity)
            }

        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if ((data[position] as? UserEntity) == null) {
            VIEW_TYPE_REPOSITORY
        } else {
            VIEW_TYPE_USER
        }
    }

    companion object {
        const val VIEW_TYPE_USER = 100
        const val VIEW_TYPE_REPOSITORY = 101
    }
}