package com.example.ttarkane.presentation

sealed class LoadingState {
    object Loading : LoadingState()
    object Success : LoadingState()
    data class Error(val errorMessage: String) :
        LoadingState()
}
