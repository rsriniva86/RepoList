package com.shyam.repolist.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.shyam.repolist.network.RepositoryNetworkService
import com.shyam.repolist.network.model.RepositoryResponseDto
import java.lang.reflect.Constructor


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