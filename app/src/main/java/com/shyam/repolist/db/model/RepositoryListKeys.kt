package com.shyam.repolist.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repoListKeys")
data class RepositoryListKeys (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val url:String
)
