package com.shyam.repolist.network.model
import com.shyam.repolist.db.model.*

object NetworkModelMapper : ModelMapper<RepositoryList,RepositoryResponseDto>{

    override fun mapToNetworkModel(model: RepositoryList): RepositoryResponseDto {
        return RepositoryResponseDto(
            pageLength = model.pageLength,
            next=model.next,
            url=model.url,
            repositoryDataDtoList = RepositoryMapper.mapToNetworkModelList(model.repositories)
        )
    }

    override fun mapFromNetworkModel(model: RepositoryResponseDto): RepositoryList {
        return RepositoryList(
                pageLength = model.pageLength,
                next=model.next,
                url=model.url,
                repositories = RepositoryMapper.mapFromNetworkModelList(model.repositoryDataDtoList)
        )
    }
}

private object RepositoryMapper: ModelMapperWithList<Repository, RepositoryDataDto>{
    override fun mapToNetworkModel(model: Repository): RepositoryDataDto {
        return RepositoryDataDto(
                scm=model.scm,
                website = model.website,
                hasWiki = model.hasWiki,
                forkPolicy = model.forkPolicy,
                fullName = model.fullName,
                name = model.name,
                language = model.language,
                createdOn = model.createdOn,
                type = model.type,
                owner = OwnerMapper.mapToNetworkModel(model.owner)
        )
    }

    override fun mapFromNetworkModel(model: RepositoryDataDto): Repository {
        return Repository(
                scm=model.scm,
                website = model.website,
                hasWiki = model.hasWiki,
                forkPolicy = model.forkPolicy,
                fullName = model.fullName,
                name = model.name,
                language = model.language,
                createdOn = model.createdOn,
                type = model.type,
                owner = OwnerMapper.mapFromNetworkModel(model.owner)
        )
    }
    override fun mapToNetworkModelList(listModel: List<Repository>?): List<RepositoryDataDto>? {
        val list:MutableList<RepositoryDataDto> = mutableListOf()
        listModel?.let {
            it.forEach { repository ->
                list.add(mapToNetworkModel(repository))
            }
        }
        return list
    }

    override fun mapFromNetworkModelList(listModel: List<RepositoryDataDto>?): List<Repository>? {
        val list:MutableList<Repository> = mutableListOf()
        listModel?.let {
            it.forEach {repositoryDataDto ->
                list.add(mapFromNetworkModel(repositoryDataDto))
            }
        }
        return list
    }
}

private object OwnerMapper:ModelMapper<RepoOwner,RepoOwnerDto>{
    override fun mapToNetworkModel(model: RepoOwner): RepoOwnerDto {
        return RepoOwnerDto(
                displayName = model.displayName,
                uuid = model.uuid,
                accountID = model.accountID,
                type = model.type,
                nickname = model.nickname,
                html = HtmlMapper.mapToNetworkModel(model.html),
                avatar = AvatarMapper.mapToNetworkModel(model.avatar),
                self =  SelfMapper.mapToNetworkModel(model.self)
                )
    }

    override fun mapFromNetworkModel(model: RepoOwnerDto): RepoOwner {
        return RepoOwner(
                displayName = model.displayName,
                uuid = model.uuid,
                accountID = model.accountID,
                type = model.type,
                nickname = model.nickname,
                html = HtmlMapper.mapFromNetworkModel(model.html),
                avatar = AvatarMapper.mapFromNetworkModel(model.avatar),
                self =  SelfMapper.mapFromNetworkModel(model.self)
        )
    }

}
private object HtmlMapper:ModelMapper<Html,HtmlDto> {
    override fun mapToNetworkModel(model: Html): HtmlDto {
        return HtmlDto(
                href=model.href
        )
    }

    override fun mapFromNetworkModel(model: HtmlDto): Html {
        return Html(
                href=model.href
        )
    }

}
private object AvatarMapper:ModelMapper<Avatar,AvatarDto> {
    override fun mapToNetworkModel(model: Avatar): AvatarDto {
        return AvatarDto(
                href=model.href
        )
    }

    override fun mapFromNetworkModel(model: AvatarDto): Avatar {
        return Avatar(
                href=model.href
        )
    }

}
private object SelfMapper:ModelMapper<Self,SelfDto> {
    override fun mapToNetworkModel(model: Self): SelfDto {
        return SelfDto(
                href=model.href
        )
    }

    override fun mapFromNetworkModel(model: SelfDto): Self {
        return Self(
                href=model.href
        )
    }

}