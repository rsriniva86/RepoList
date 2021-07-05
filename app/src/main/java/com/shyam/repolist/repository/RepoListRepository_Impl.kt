package com.shyam.repolist.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shyam.repolist.db.model.RepositoryList
import com.shyam.repolist.network.NetworkConstants
import com.shyam.repolist.network.RepositoryNetworkService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoListRepository_Impl
    @Inject
    constructor(
        networkService: RepositoryNetworkService
    )
    : RepoListRepository {
    override suspend fun getRepoList(
        query: String,
        autoCorrect: Boolean
    ): Flow<PagingData<RepositoryList>> {
        return Pager(
            PagingConfig(pageSize = 10, enablePlaceholders = false),
            remoteMediator = SearchImageRemoteMediator(
                networkService = networkService,
                query = query,
                autoCorrect = autoCorrect,
                networkMapper = networkMapper
            ),
            pagingSourceFactory = {
                databaseService.imageDao().selectAll(query)
            }
        ).flow
    }
}