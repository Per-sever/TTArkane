package com.example.ttarkane.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ttarkane.data.ApiFactory
import com.example.ttarkane.data.models.RepositoryEntity
import com.example.ttarkane.data.models.UserEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class SearchInfoViewModel : ViewModel() {

    private val infoListGH = ApiFactory.apiService

    private val _userList = MutableLiveData<List<UserEntity>>()
    val userList: LiveData<List<UserEntity>> = _userList

    private val _repoList = MutableLiveData<List<RepositoryEntity>>()
    val repoList: LiveData<List<RepositoryEntity>> = _repoList

    fun loadData(input: String) {
        viewModelScope.launch {
            try {
                val responseUserDeferred = async { infoListGH.getUsersList(input).items }
                val responseRepoDeferred = async { infoListGH.getRepositoriesList(input).items }
                _userList.value = responseUserDeferred.await()
                _repoList.value =  responseRepoDeferred.await()
            } catch (e: Exception) {
            }
        }
    }

}