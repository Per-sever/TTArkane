package com.example.ttarkane.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.ttarkane.R
import com.example.ttarkane.data.models.GitHubInfoEntity
import com.example.ttarkane.data.models.RepositoryEntity
import com.example.ttarkane.databinding.RepositoryItemBinding

class GitHubRepoUserAdapter : ListAdapter<GitHubInfoEntity, GitHubInfoViewHolder>
    (GitHubInfoListCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubInfoViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_REPOSITORY -> R.layout.repository_item
            VIEW_TYPE_USER -> R.layout.user_item
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return GitHubInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitHubInfoViewHolder, position: Int) {
        val gitHubInfoItem = getItem(position)
        val binding = holder.binding
        when (binding) {
            is RepositoryItemBinding -> {
                binding.repositoryEntity = gitHubInfoItem as RepositoryEntity?
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val gitHubInfoItem = getItem(position)
        return if (gitHubInfoItem.owner != null) {
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