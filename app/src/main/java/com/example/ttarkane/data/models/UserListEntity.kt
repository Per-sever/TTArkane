package com.example.ttarkane.data.models

import androidx.lifecycle.LiveData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class UserListEntity(
    @SerializedName("total_count")
    @Expose
    var totalCount: Int? = null,

    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean? = null,

    @SerializedName("items")
    @Expose
    var items: List<UserEntity>? = null,
)
