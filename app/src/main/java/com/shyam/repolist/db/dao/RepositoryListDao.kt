package com.shyam.repolist.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shyam.repolist.db.model.RepositoryList

@Dao
interface RepositoryListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(repoList: RepositoryList)

    @Query("SELECT * FROM repository_list WHERE `url` = :q")
    fun selectByUrl(q: String?): PagingSource<Int, RepositoryList>

}