package com.shyam.repolist.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.shyam.repolist.db.RepositoryDatabaseService
import com.shyam.repolist.db.model.Repository
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
) : RemoteMediator<Int, Repository>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Repository>
    ): MediatorResult {
        return try {
            Log.i(TAG, "load")

            val networkResponse = networkService.getRepoList()
            Log.i(TAG, "networkResponse=$networkResponse")
            networkResponse.url=NetworkConstants.BASE_URL+"repositories"
            networkResponse.repositoryDataDtoList?.forEachIndexed {
                    index, repositoryDataDto ->
                repositoryDataDto.urlWithIndex=networkResponse.url+ index

            }
            val repositoryList = NetworkModelMapper.mapFromNetworkModelList(networkResponse.repositoryDataDtoList)

            if (repositoryList?.isNotEmpty() == true) {
                databaseService.withTransaction {
                    repositoryList.forEach {
                        databaseService.repoListDao().saveAll(it)
                    }
                }
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