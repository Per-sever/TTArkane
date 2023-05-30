package com.example.ttarkane.presentation.adapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ttarkane.data.models.RepositoryEntity
import com.example.ttarkane.data.models.UserEntity
import com.example.ttarkane.databinding.RepositoryItemBinding
import com.example.ttarkane.databinding.UserItemBinding

class UserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: UserEntity) {
        with(binding) {
            tvUserLogin.text = item.login
            tvUserScore.text = item.score.toString()
            Glide.with(binding.root).load(item.avatarUrl).into(ivUserAvatar)
        }

    }


}

class RepositoryViewHolder(val binding: RepositoryItemBinding) : RecyclerView.ViewHolder(
    binding
        .root
) {
    fun bind(item: RepositoryEntity) {
        with(binding) {
            tvNameRepo.text = item.name
            tvRepoDescr.text = item.description
            tvForksCount.text = formattingCountForks(item.forks ?: 0)
        }
    }

    private fun formattingCountForks(count: Int): String {
        return "$count \n Forks"
    }
}


