package com.shyam.repolist.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shyam.repolist.db.model.Key

@Dao
interface KeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceRemoteKeys(key: Key)

    @Query("SELECT * FROM keys")
    suspend fun selectAll(): List<Key>

    @Query("DELETE FROM keys")
    suspend fun deleteAll()
}