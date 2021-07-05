package com.shyam.repolist.network.model

import com.google.gson.annotations.SerializedName


data class RepositoryResponseDto(
    @SerializedName("pagelen")
    val pageLength:Int,
    @SerializedName("next")
    val next:String,
    @SerializedName("values")
    val repositoryDataDtoList: List<RepositoryDataDto>
)

data class RepositoryDataDto (

    @SerializedName("scm")
    val scm:String,
    @SerializedName("website")
    val website:String,
    @SerializedName("has_wiki")
    val hasWiki:Boolean,
    @SerializedName("fork_policy")
    val forkPolicy:String,
    @SerializedName("full_name")
    val fullName:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("language")
    val language:String,
    @SerializedName("created_on")
    val createdOn:String,
    @SerializedName("owner")
    val owner: RepoOwnerDto,
    @SerializedName("type")
    val type:String,

    )

data class RepoOwnerDto (
    @SerializedName("display_name")
    val displayName:String,
    @SerializedName("uuid")
    val uuid:String,
    @SerializedName("self")
    val self:Self,
    @SerializedName("html")
    val html:Html,
    @SerializedName("avatar")
    val avatar: Avatar,
    @SerializedName("type")
    val type:String,
    @SerializedName("nickname")
    val nickname:String,
    @SerializedName("account_id")
    val accountID:String
)

data class Self(
    @SerializedName("href")
    val href:String
    )
data class Html(
    @SerializedName("href")
    val href:String
    )
data class Avatar(
    @SerializedName("href")
    val href:String
    )