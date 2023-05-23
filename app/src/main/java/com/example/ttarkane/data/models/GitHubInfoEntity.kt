package com.example.ttarkane.data.models

open class GitHubInfoEntity(open val id: Int?, open val owner: RepositoryOwnerEntity?) {

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (owner?.hashCode() ?: 0)
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GitHubInfoEntity

        if (id != other.id) return false
        if (owner != other.owner) return false

        return true
    }
}