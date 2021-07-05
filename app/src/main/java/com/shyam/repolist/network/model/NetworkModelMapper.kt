package com.shyam.repolist.network.model
import com.shyam.repolist.db.model.Repository
import com.shyam.repolist.db.model.RepositoryList

object NetworkModelMapper : ModelMapper<RepositoryList,RepositoryResponseDto>{


    override fun mapToNetworkModel(model: RepositoryList): RepositoryResponseDto {
        return RepositoryResponseDto(
            pageLength = model.pageLength,
            next=model.next,
        );
    }

    override fun mapFromNetworkModel(model: RepositoryResponseDto): RepositoryList {

    }

    private class RepositoryMapper: ModelMapper<Repository, RepositoryDataDto>{
        override fun mapToNetworkModel(model: Repository): RepositoryDataDto {
            return RepositoryDataDto(
                scm=model.scm,

            )
        }

        override fun mapFromNetworkModel(model: RepositoryDataDto): Repository {
            return Repository(

            )
        }

    }

    override fun mapToNetworkModelList(listModel: List<RepositoryList>): List<RepositoryResponseDto> {
        TODO("Not yet implemented")
    }

    override fun mapFromNetworkModelList(listModel: List<RepositoryResponseDto>): List<RepositoryList> {
        TODO("Not yet implemented")
    }


}