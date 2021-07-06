package com.shyam.repolist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shyam.repolist.db.dao.RepositoryListDao
import com.shyam.repolist.db.model.Repository
import javax.inject.Singleton

@Singleton
@Database(
        entities = [
            Repository::class
        ],
        exportSchema = false,
        version = 1
)
@TypeConverters(DataConverter::class)
abstract class RepositoryDatabaseService : RoomDatabase() {
    abstract fun repoListDao(): RepositoryListDao
}