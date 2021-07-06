package com.shyam.repolist.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "keys")
data class Key(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val key: String
)