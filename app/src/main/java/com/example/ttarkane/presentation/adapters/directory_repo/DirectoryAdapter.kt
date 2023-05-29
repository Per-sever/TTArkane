package com.example.ttarkane.presentation.adapters.directory_repo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.ttarkane.R
import com.example.ttarkane.data.models.DirectoryEntity
import com.example.ttarkane.data.models.UserEntity
import com.example.ttarkane.databinding.DirectoryItemBinding

class DirectoryAdapter :
    ListAdapter<DirectoryEntity, DirectoryViewHolder>(DirectoryDiffCallback()) {

    var onFileClickListener: ((DirectoryEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectoryViewHolder {
        val binding = DirectoryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return DirectoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DirectoryViewHolder, position: Int) {
        val directoryItem = getItem(position)
        val binding = holder.binding
        if (directoryItem.type == FOLDER_TYPE) {
            binding.ivIconDirectory.setImageResource(R.drawable.folder_icon)
        } else {
            binding.ivIconDirectory.setImageResource(R.drawable.file_icon)
            holder.itemView.setOnClickListener() {
                onFileClickListener?.invoke(directoryItem)
            }
        }
        binding.tvItemName.text = directoryItem.name
    }

    companion object {
        private const val FOLDER_TYPE = "dir"
        private const val FILE_TYPE = "file"
    }
}