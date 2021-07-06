package com.shyam.repolist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shyam.repolist.db.dao.RepositoryListDao
import com.shyam.repolist.db.model.RepositoryListKeys
import com.shyam.repolist.db.model.RepositoryList
import javax.inject.Singleton

@Singleton
@Database(
        entities = [
            RepositoryList::class,
            RepositoryListKeys::class
        ],
        exportSchema = false,
        version = 1
)
abstract class RepositoryDatabaseService : RoomDatabase() {
    abstract fun repoListDao(): RepositoryListDao
    abstract fun remoteKeyDao(): RepositoryListKeys
}