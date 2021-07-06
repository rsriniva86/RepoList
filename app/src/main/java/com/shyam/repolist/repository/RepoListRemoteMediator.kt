package com.shyam.repolist.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.shyam.repolist.db.RepositoryDatabaseService
import com.shyam.repolist.db.model.Key
import com.shyam.repolist.db.model.Repository
import com.shyam.repolist.network.NetworkConstants
import com.shyam.repolist.network.RepositoryNetworkService
import com.shyam.repolist.network.model.NetworkModelMapper
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class RepoListRemoteMediator constructor(
    private val networkService: RepositoryNetworkService,
    private val databaseService: RepositoryDatabaseService,
) : RemoteMediator<Int, Repository>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Repository>
    ): MediatorResult {
        return try {

            Log.i(TAG, "load")
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    databaseService.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            databaseService.keyDao().deleteAll()
                            databaseService.repoListDao().deleteAll()
                        }
                    }
                    null
                    }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    val key = getRemoteKeys()
                    key
                }
            }
            val networkResponse =loadKey?.let{
                Log.i(TAG, "getRepoListWithQuery, key=${decoded(it.key)}")
                networkService.getRepoListWithQuery(decoded(it.key))
            } ?:networkService.getRepoList()

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

                        databaseService.repoListDao().save(it)
                    }
                    networkResponse.next?.let{
                        Log.i(TAG,"next=${getKeyFromNextUrl(it)}")
                        databaseService.keyDao().deleteAll()
                        databaseService
                            .keyDao()
                            .insertOrReplaceRemoteKeys(
                                Key(0, getKeyFromNextUrl(it)
                            ))
                    }
                }
            }

            val isNextAbsent:Boolean = networkResponse.next.isNullOrBlank()
            return MediatorResult.Success(endOfPaginationReached = true)

        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }


    }

    private fun decoded(urlQuery: String): String {
       return java.net.URLDecoder.decode(urlQuery, "UTF-8")
    }

    private fun getKeyFromNextUrl(fullURL: String): String {
        return fullURL.substring("https://api.bitbucket.org/2.0/repositories?after=".length)
    }

    private suspend fun getRemoteKeys(): Key? {
        return databaseService.keyDao().selectAll().firstOrNull()
    }

    companion object {
        val TAG = RepoListRemoteMediator::class.simpleName
    }
}