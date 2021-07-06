package com.shyam.repolist.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.shyam.repolist.db.model.RepositoryListKeys

@Dao
interface RepositoryListKeysDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertOrReplaceKeys(repositoryListKeys: RepositoryListKeys)

    @Query("SELECT * FROM repoListKeys WHERE `url` = :url ORDER BY id DESC")
    suspend fun keyByUrl(url: String): List<RepositoryListKeys>

    @Query("SELECT * FROM repoListKeys")
    suspend fun selectAllKeys(): List<RepositoryListKeys>

    @Query("DELETE FROM repoListKeys WHERE `url` = :url")
    suspend fun deleteByUrl(url: String)

}