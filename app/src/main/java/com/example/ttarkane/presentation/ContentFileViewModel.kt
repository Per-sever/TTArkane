package com.example.ttarkane.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ttarkane.data.ApiFactory
import com.example.ttarkane.data.models.DirectoryEntity
import com.example.ttarkane.data.models.FileEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ContentFileViewModel : ViewModel() {

    private val infoListGH = ApiFactory.apiService

    private val _fileEntity = MutableLiveData<FileEntity>()
    val fileEntity: LiveData<FileEntity> = _fileEntity


    fun loadData(directoryEntity: DirectoryEntity?, owner: String?, repo: String?) {
        viewModelScope.launch {
            try {
                val responseFileDeferred = async {
                    directoryEntity?.path?.let {
                        infoListGH.getFileItem(
                            owner, repo,
                            it
                        )
                    }
                }
                _fileEntity.value = responseFileDeferred.await()
            } catch (e: Exception) {
            }
        }
    }
}