package com.shyam.repolist.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.shyam.repolist.db.RepositoryDatabaseService
import com.shyam.repolist.db.model.RepositoryList
import com.shyam.repolist.network.NetworkConstants
import com.shyam.repolist.network.RepositoryNetworkService
import com.shyam.repolist.network.model.NetworkModelMapper
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RepoListRemoteMediator @Inject constructor(
    private val networkService: RepositoryNetworkService,
    private val databaseService: RepositoryDatabaseService
) : RemoteMediator<Int, RepositoryList>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepositoryList>
    ): MediatorResult {
        return try {

            val networkResponse = networkService.getRepoList();
            networkResponse.url=NetworkConstants.BASE_URL+"repositories"
            val repositoryList = NetworkModelMapper.mapFromNetworkModel(networkResponse)

            if (repositoryList.repositories?.isNotEmpty() == true) {
                databaseService.repoListDao().saveAll(repoList = repositoryList)
            }

            return MediatorResult.Success(endOfPaginationReached = true)

        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }

    }


    companion object {
        val TAG = RepoListRemoteMediator::class.simpleName
    }
}