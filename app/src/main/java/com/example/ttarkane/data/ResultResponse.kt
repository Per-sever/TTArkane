package com.example.ttarkane.data

import com.example.ttarkane.data.models.RepositoryEntity
import com.example.ttarkane.data.models.UserEntity

sealed class ResultResponse{
    data class SuccessUser(val userListEntity: List<UserEntity>): ResultResponse()
    data class SuccessRepo(val repoListEntity: List<RepositoryEntity>): ResultResponse()
    data class Error(val exception: Throwable): ResultResponse()
}
