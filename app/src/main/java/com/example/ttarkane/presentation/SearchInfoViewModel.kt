package com.example.ttarkane.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ttarkane.data.ApiFactory
import com.example.ttarkane.data.models.RepositoryEntity
import com.example.ttarkane.data.models.UserEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SearchInfoViewModel : ViewModel() {

    private val infoListGH = ApiFactory.apiService

    private val _userList = MutableLiveData<List<UserEntity>>()
    val userList: LiveData<List<UserEntity>> = _userList

    private val _repoList = MutableLiveData<List<RepositoryEntity>>()
    val repoList: LiveData<List<RepositoryEntity>> = _repoList

    private val _errorInput = MutableLiveData<Boolean>()
    val errorInput: LiveData<Boolean> = _errorInput

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> = _loadingState

    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String> = _inputText

    private val _isButtonEnabled: LiveData<Boolean> = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    fun loadData(input: String) {
        _loadingState.value = LoadingState.Loading
        val validField = validateInput(input)
        if (validField) {
            viewModelScope.launch {
                try {
                    val responseUserDeferred = async { infoListGH.getUsersList(input).items }
                    val responseRepoDeferred = async { infoListGH.getRepositoriesList(input).items }
                    _userList.value = responseUserDeferred.await()
                    _repoList.value = responseRepoDeferred.await()
                    _loadingState.value = LoadingState.Success
                } catch (e: Exception) {
                    _loadingState.value = LoadingState.Error(e.toString())
                }
            }
        }

    }

    private fun validateInput(input: String): Boolean {
        var result = true
        if (input.isBlank() || input.length < 3) {
            _errorInput.value = true
            result = false
        }
        return result
    }


    fun resetInputError() {
        _errorInput.value = false
    }

    fun inputText(input: String) {
        _inputText.value = input
    }

}