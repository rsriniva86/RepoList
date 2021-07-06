package com.shyam.repolist.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shyam.repolist.db.RepositoryDatabaseService
import com.shyam.repolist.db.model.RepositoryList
import com.shyam.repolist.network.NetworkConstants
import com.shyam.repolist.network.RepositoryNetworkService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoListRepository_Impl
    @Inject
    constructor(
       private val networkService: RepositoryNetworkService,
       private val databaseService: RepositoryDatabaseService
    )
    : RepoListRepository {

    override suspend fun getRepoList(
        urlQueryString: String
    ): Flow<PagingData<RepositoryList>> {
        return Pager(
            PagingConfig(pageSize = 10, enablePlaceholders = false),
            remoteMediator = RepoListRemoteMediator(
                    networkService=networkService,
                    databaseService=databaseService
            ),
            pagingSourceFactory = {
                databaseService
                        .repoListDao()
                        .selectByUrl(NetworkConstants.BASE_URL+urlQueryString)
            }
        ).flow
    }
}