package com.shyam.repolist.di

import android.content.Context
import androidx.room.Room
import com.shyam.repolist.db.RepositoryDatabaseService
import com.shyam.repolist.db.dao.RepositoryListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideImageDao(databaseService: RepositoryDatabaseService): RepositoryListDao {
        return databaseService.repoListDao()
    }



    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): RepositoryDatabaseService {
        return Room.databaseBuilder(
            appContext,
            RepositoryDatabaseService::class.java,
            "RepolistApp"
        ).build()
    }
}