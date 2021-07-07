package com.shyam.repolist.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shyam.repolist.db.model.Repository
import com.shyam.repolist.repository.RepoListRepository
import kotlinx.coroutines.flow.Flow


class MainViewModel
    @ViewModelInject
    constructor(
        private val repoListRepository: RepoListRepository
        ) : ViewModel() {

       suspend fun getRepoList(): Flow<PagingData<Repository>> {
           return repoListRepository
                   .getRepoList()
                   .cachedIn(viewModelScope)
       }

}