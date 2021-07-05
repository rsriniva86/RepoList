package com.shyam.repolist.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.shyam.repolist.network.RepositoryNetworkService


class MainViewModel
    @ViewModelInject
    constructor(
        val networkService: RepositoryNetworkService
        ) : ViewModel() {

       suspend fun testNetwork(){
           val repositoryResponseDto =networkService.getRepoList();
           System.out.println("Response="+repositoryResponseDto)
       }

}