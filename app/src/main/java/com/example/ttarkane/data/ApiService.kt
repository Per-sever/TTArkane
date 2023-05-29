package com.example.ttarkane.data

import com.example.ttarkane.data.models.DirectoryEntity
import com.example.ttarkane.data.models.FileEntity
import com.example.ttarkane.data.models.RepositoryListEntity
import com.example.ttarkane.data.models.UserListEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun getUsersList(
        @Query("q") searchInput: String,
        @Query("per_page") perPage: Int = 10,
    ): UserListEntity

    @GET("search/repositories")
    suspend fun getRepositoriesList(
        @Query("q") searchInput: String,
        @Query("per_page") perPage: Int = 10,
    ): RepositoryListEntity

    @GET("repos/{owner}/{repo}/contents")
    suspend fun getDirectoryRepo(@Path("owner") owner: String, @Path("repo") repo: String):
            List<DirectoryEntity>

    @GET("/repos/{owner}/{repo}/contents/{fileName}?ref=master")
    suspend fun getFileItem(
        @Path("owner") owner: String?, @Path("repo") repo: String?,
        @Path
            ("fileName") fileName:
        String,
    ): FileEntity
}