package com.example.ttarkane.data.models

import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinkEntity(
    @SerializedName("self")
    @Expose
    var self: String? = null,

    @SerializedName("git")
    @Expose
    var git: String? = null,

    @SerializedName("html")
    @Expose
    var html: String? = null,
) : Parcelable