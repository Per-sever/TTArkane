package com.example.ttarkane.data.models

import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DirectoryEntity(

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("path")
    @Expose
    var path: String? = null,

    @SerializedName("sha")
    @Expose
    var sha: String? = null,

    @SerializedName("size")
    @Expose
    var size: Int? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("html_url")
    @Expose
    var htmlUrl: String? = null,

    @SerializedName("git_url")
    @Expose
    var gitUrl: String? = null,

    @SerializedName("download_url")
    @Expose
    var downloadUrl: String? = null,

    @SerializedName("type")
    @Expose
    var type: String? = null,

    @SerializedName("_links")
    @Expose
    var links: LinkEntity? = null,
) : Parcelable
