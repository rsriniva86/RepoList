package com.shyam.repolist.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shyam.repolist.db.RepositoryDatabaseService
import com.shyam.repolist.db.model.Repository
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
    ): Flow<PagingData<Repository>> {
        Log.i(TAG,"getRepoList")
        return Pager(
            PagingConfig(
                pageSize = 3,
                enablePlaceholders = false,
                prefetchDistance = 1),
            remoteMediator = RepoListRemoteMediator(
                    networkService=networkService,
                    databaseService=databaseService
            ),
            pagingSourceFactory = {

                databaseService
                        .repoListDao()
                        .selectAll()

            }
        ).flow
    }
    companion object{
        val TAG = RepoListRepository_Impl::class.simpleName
    }
}