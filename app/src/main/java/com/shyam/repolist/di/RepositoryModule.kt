package com.shyam.repolist.di

import com.shyam.repolist.db.RepositoryDatabaseService
import com.shyam.repolist.network.RepositoryNetworkService
import com.shyam.repolist.repository.RepoListRepository
import com.shyam.repolist.repository.RepoListRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepoListRepository(
        networkService: RepositoryNetworkService,
        databaseService: RepositoryDatabaseService

    ): RepoListRepository {
        return RepoListRepository_Impl(networkService,  databaseService)
    }
}