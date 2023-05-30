package com.example.ttarkane.presentation.adapters.directory_repo

import androidx.recyclerview.widget.DiffUtil
import com.example.ttarkane.data.models.DirectoryEntity

class DirectoryDiffCallback : DiffUtil.ItemCallback<DirectoryEntity>() {
    override fun areItemsTheSame(oldItem: DirectoryEntity, newItem: DirectoryEntity): Boolean {
        return oldItem.sha == newItem.sha;
    }

    override fun areContentsTheSame(oldItem: DirectoryEntity, newItem: DirectoryEntity): Boolean {
        return oldItem == newItem;
    }
}