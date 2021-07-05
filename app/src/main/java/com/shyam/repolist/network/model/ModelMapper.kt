package com.shyam.repolist.network.model

interface ModelMapper <T, U> {
    fun mapToNetworkModel(model: T): U
    fun mapFromNetworkModel(model: U): T
    fun mapToNetworkModelList(listModel:List<T>):List<U>
    fun mapFromNetworkModelList(listModel:List<U>): List<T>

}