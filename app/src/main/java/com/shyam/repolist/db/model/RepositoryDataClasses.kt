package com.shyam.repolist.db.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository_list")
data class RepositoryList(
   @PrimaryKey
   @NonNull
   @ColumnInfo(name = "url")
   val url:String,
   @ColumnInfo(name ="pagelen")
   val pageLength:Int,
   @ColumnInfo(name ="next")
   val next:String,
   @ColumnInfo(name ="values")
   val repositories: List<Repository>?
)

data class Repository (

   @ColumnInfo(name ="scm")
   val scm:String,
   @ColumnInfo(name ="website")
   val website:String,
   @ColumnInfo(name ="has_wiki")
   val hasWiki:Boolean,
   @ColumnInfo(name ="fork_policy")
   val forkPolicy:String,
   @ColumnInfo(name ="full_name")
   val fullName:String,
   @ColumnInfo(name ="name")
   val name:String,
   @ColumnInfo(name ="language")
   val language:String,
   @ColumnInfo(name ="created_on")
   val createdOn:String,
   @ColumnInfo(name ="owner")
   val owner: RepoOwnerDto,
   @ColumnInfo(name ="type")
   val type:String,

   )

data class RepoOwnerDto (
   @ColumnInfo(name ="display_name")
   val displayName:String,
   @ColumnInfo(name ="uuid")
   val uuid:String,
   @ColumnInfo(name ="self")
   val self:Self,
   @ColumnInfo(name ="html")
   val html:Html,
   @ColumnInfo(name ="avatar")
   val avatar: Avatar,
   @ColumnInfo(name ="type")
   val type:String,
   @ColumnInfo(name ="nickname")
   val nickname:String,
   @ColumnInfo(name ="account_id")
   val accountID:String
)

data class Self(
   @ColumnInfo(name ="href")
   val href:String
)
data class Html(
   @ColumnInfo(name ="href")
   val href:String
)
data class Avatar(
   @ColumnInfo(name ="href")
   val href:String
)