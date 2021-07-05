package com.shyam.repolist.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.shyam.repolist.db.model.RepositoryList
import com.shyam.repolist.network.NetworkConstants
import com.shyam.repolist.network.RepositoryNetworkService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RepoListRemoteMediator @Inject constructor(
    private val networkService: RepositoryNetworkService,
    private val databaseService: DatabaseService
) : RemoteMediator<Int, RepositoryList>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepositoryList>
    ): MediatorResult {
        return try {

            val networkResponse = networkService.getRepoList();

            val repositoryDataDtoList = networkResponse.repositoryDataDtoList
            val repositoryList = networkMapper.toDomainList(repositoryDataDtoList, query)

            if (repositoryList.isNotEmpty()) {
                databaseService.imageDao().saveAllImageData(repositoryList)
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