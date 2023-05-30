package com.example.ttarkane.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ttarkane.data.ApiFactory
import com.example.ttarkane.data.models.DirectoryEntity
import kotlinx.coroutines.launch

class DirectoryViewModel : ViewModel() {

    private val directoryListGH = ApiFactory.apiService

    private val _directoryList = MutableLiveData<List<DirectoryEntity>>()
    val directoryList: LiveData<List<DirectoryEntity>> = _directoryList


    fun loadData(owner: String?, repo: String?) {
        viewModelScope.launch {
            _directoryList.value = directoryListGH.getRootDirectoryRepo(owner, repo)
        }
    }

    fun loadData(owner: String?, repo: String?, name: String?) {
        viewModelScope.launch {
            _directoryList.value = directoryListGH.getDirectoryRepo(owner, repo, name)
        }
    }
}