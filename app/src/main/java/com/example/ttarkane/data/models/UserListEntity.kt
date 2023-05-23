package com.example.ttarkane.data.models

import android.content.ClipData.Item
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class UserListEntity {
    @SerializedName("total_count")
    @Expose
    var totalCount: Int? = null

    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean? = null

    @SerializedName("items")
    @Expose
    var items: List<UserEntity>? = null
}
