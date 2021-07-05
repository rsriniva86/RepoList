package com.shyam.repolist.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shyam.repolist.db.model.RepositoryList

@Dao
interface RepoListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllImageData(repoList: RepositoryList)

    @Query("SELECT * FROM repository_list WHERE `url` = :q")
    fun selectAll(q: String?): PagingSource<Int, RepositoryList>

}