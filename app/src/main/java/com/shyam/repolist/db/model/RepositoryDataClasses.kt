package com.shyam.repolist.db.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "repository_list", primaryKeys = ["url","index"])
data class Repository (

   @NonNull
   @ColumnInfo(name ="url")
   var url:String,

   @NonNull
   @ColumnInfo(name ="index")
   var index:Int,

   @ColumnInfo(name ="scm")
   val scm:String?,
   @ColumnInfo(name ="website")
   val website:String?,
   @ColumnInfo(name ="has_wiki")
   val hasWiki:Boolean?,
   @ColumnInfo(name ="fork_policy")
   val forkPolicy:String?,
   @ColumnInfo(name ="full_name")
   val fullName:String?,
   @ColumnInfo(name ="name")
   val name:String?,
   @ColumnInfo(name ="language")
   val language:String?,
   @ColumnInfo(name ="created_on")
   val createdOn:String?,
   @Embedded
   val owner: RepoOwner?,
   @ColumnInfo(name ="repo_type")
   val type:String?,

   ) : Serializable

data class RepoOwner (
   @ColumnInfo(name ="display_name")
   val displayName:String?,
   @ColumnInfo(name ="uuid")
   val uuid:String?,
   @Embedded
   val links:Links?,
   @ColumnInfo(name ="owner_type")
   val type:String?,
   @ColumnInfo(name ="nickname")
   val nickname:String?,
   @ColumnInfo(name ="account_id")
   val accountID:String?
) : Serializable

data class Links(
   @Embedded
   val self:Self?,
   @Embedded
   val html:Html?,
   @Embedded
   val avatar: Avatar?,
   )

data class Self(
   @ColumnInfo(name ="selfhref")
   val href:String?
)
data class Html(
   @ColumnInfo(name ="htmlhref")
   val href:String?
)
data class Avatar(
   @ColumnInfo(name ="avatarhref")
   val href:String?
)

