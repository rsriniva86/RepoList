package com.shyam.repolist.repository

import androidx.paging.PagingData
import com.shyam.repolist.db.model.Repository
import kotlinx.coroutines.flow.Flow

interface RepoListRepository {

    suspend fun getRepoList(
        urlQueryString: String,
    ): Flow<PagingData<Repository>>

}