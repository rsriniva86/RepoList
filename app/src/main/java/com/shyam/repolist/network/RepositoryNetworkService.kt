package com.shyam.repolist.network

import com.shyam.repolist.network.model.RepositoryResponseDto
import retrofit2.http.GET

interface RepositoryNetworkService {

    @GET("repositories")
    suspend fun getRepoList(): RepositoryResponseDto
}