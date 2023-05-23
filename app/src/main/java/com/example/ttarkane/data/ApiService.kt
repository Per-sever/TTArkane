package com.example.ttarkane.data

import com.example.ttarkane.data.models.RepositoryListEntity
import com.example.ttarkane.data.models.UserListEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun getUsersList(
        @Query("q") searchInput: String,
        @Query("per_page") perPage: Int = 10
    ): UserListEntity

    @GET("search/repositories")
    suspend fun getRepositoriesList(
        @Query("q") searchInput: String,
        @Query("per_page") perPage: Int = 10
    ): RepositoryListEntity
}