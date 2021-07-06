package com.shyam.repolist.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shyam.repolist.db.model.Repository

@Dao
interface RepositoryListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(repo: Repository)

    @Query("SELECT * FROM repository_list WHERE `url` = :url")
    fun selectByUrl(url: String?): PagingSource<Int, Repository>

    @Query("SELECT * FROM repository_list ")
    fun selectAll(): PagingSource<Int, Repository>

}