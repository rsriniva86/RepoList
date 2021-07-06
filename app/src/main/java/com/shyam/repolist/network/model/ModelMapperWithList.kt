package com.shyam.repolist.network.model

interface ModelMapperWithList<T,U>: ModelMapper<T,U> {
    fun mapToNetworkModelList(listModel:List<T>?):List<U>?
    fun mapFromNetworkModelList(listModel:List<U>?): List<T>?

}