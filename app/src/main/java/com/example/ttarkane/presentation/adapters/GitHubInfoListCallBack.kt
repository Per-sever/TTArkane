package com.example.ttarkane.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.ttarkane.data.models.GitHubInfoEntity

class GitHubInfoListCallBack() : DiffUtil.ItemCallback<GitHubInfoEntity>() {
    override fun areItemsTheSame(oldItem: GitHubInfoEntity, newItem: GitHubInfoEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GitHubInfoEntity, newItem: GitHubInfoEntity): Boolean {
        return oldItem == newItem
    }
}