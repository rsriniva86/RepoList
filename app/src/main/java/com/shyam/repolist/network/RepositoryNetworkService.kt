package com.shyam.repolist.network

import com.shyam.repolist.network.model.RepositoryResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryNetworkService {

    @GET("repositories")
    suspend fun getRepoList(): RepositoryResponseDto

    @GET("repositories")
    suspend fun getRepoListWithQuery(
        @Query("after") after: String
    ): RepositoryResponseDto

}