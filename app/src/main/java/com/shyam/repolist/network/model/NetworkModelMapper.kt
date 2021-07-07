package com.shyam.repolist.network.model
import com.shyam.repolist.db.model.*

object NetworkModelMapper : ModelMapperWithList<Repository, RepositoryDataDto>{
    override fun mapToNetworkModel(model: Repository): RepositoryDataDto {
        model.owner
        return RepositoryDataDto(
            url = model.url,
            index = model.index,
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
            url = model.url,
            index = model.index,
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


private object OwnerMapper:ModelMapper<RepoOwner?,RepoOwnerDto?>{
    override fun mapToNetworkModel(model: RepoOwner?): RepoOwnerDto? {
        return model?.let {
            RepoOwnerDto(
                displayName = it.displayName,
                uuid = model.uuid,
                accountID = model.accountID,
                type = model.type,
                nickname = model.nickname,
                links = LinksMapper.mapToNetworkModel(model.links)
            )
        }
    }

    override fun mapFromNetworkModel(model: RepoOwnerDto?): RepoOwner? {
        return model?.let {
            RepoOwner(
                displayName = it.displayName,
                uuid = model.uuid,
                accountID = model.accountID,
                type = model.type,
                nickname = model.nickname,
                links = LinksMapper.mapFromNetworkModel(model.links)

            )
        }
    }

}

private object LinksMapper:ModelMapper<Links?,LinksDto?> {
    override fun mapToNetworkModel(model: Links?): LinksDto? {
        return model?.let {
            LinksDto(
                html = HtmlMapper.mapToNetworkModel(model.html),
                avatar = AvatarMapper.mapToNetworkModel(model.avatar),
                self =  SelfMapper.mapToNetworkModel(model.self)

            )
        }
    }

    override fun mapFromNetworkModel(model: LinksDto?): Links? {
        return model?.let {
            return model?.let {
                Links(
                    html = HtmlMapper.mapFromNetworkModel(model.html),
                    avatar = AvatarMapper.mapFromNetworkModel(model.avatar),
                    self =  SelfMapper.mapFromNetworkModel(model.self)
                )
            }
        }

    }

}
private object HtmlMapper:ModelMapper<Html?,HtmlDto?> {
    override fun mapToNetworkModel(model: Html?): HtmlDto? {
        return model?.let {
            HtmlDto(
                href= it.href
            )
        }
    }

    override fun mapFromNetworkModel(model: HtmlDto?): Html? {
        return model?.let {
            Html(
                href= it.href
            )
        }
    }

}
private object AvatarMapper:ModelMapper<Avatar?,AvatarDto?> {
    override fun mapToNetworkModel(model: Avatar?): AvatarDto? {
        return model?.let {
            AvatarDto(
                href= it.href
            )
        }
    }

    override fun mapFromNetworkModel(model: AvatarDto?): Avatar? {
        return model?.let {
            Avatar(
                href= it.href
            )
        }
    }

}
private object SelfMapper:ModelMapper<Self?,SelfDto?> {
    override fun mapToNetworkModel(model: Self?): SelfDto? {
        return model?.let {
            SelfDto(
                href= it.href
            )
        }
    }

    override fun mapFromNetworkModel(model: SelfDto?): Self? {
        return model?.let {
            Self(
                href= it.href
            )
        }
    }

}