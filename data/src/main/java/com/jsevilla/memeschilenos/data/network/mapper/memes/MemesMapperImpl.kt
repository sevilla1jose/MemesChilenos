package com.jsevilla.memeschilenos.data.network.mapper.memes

import com.jsevilla.memeschilenos.data.local.db.AppDatabase
import com.jsevilla.memeschilenos.data.local.entity.MemeLocalEntity
import com.jsevilla.memeschilenos.data.network.response.ChildrenDataResponse
import com.jsevilla.memeschilenos.data.network.response.ChildrenResponse
import com.jsevilla.memeschilenos.data.network.response.MemeResponse
import com.jsevilla.memeschilenos.domain.entity.ChildrenDataEntity
import com.jsevilla.memeschilenos.domain.entity.ChildrenEntity
import com.jsevilla.memeschilenos.domain.entity.MemeEntity

class MemesMapperImpl(
    private val db: AppDatabase
) : MemesMapper {
    override suspend fun getMemesDataToDomain(data: MemeResponse): MemeEntity {
        db.memesDao().deleteAll()

        val listChildren: MutableList<ChildrenResponse> = arrayListOf()
        data.children.forEach { children ->
            if (children.data.linkFlairText == "Shitposting" && children.data.postHint == "image") {
                listChildren.add(children)
            }
        }

        return MemeEntity(
            children = listChildren.map {
                getChildrenDataToDomain(it)
            }.toMutableList()
        )
    }

    override suspend fun getChildrenDataToDomain(data: ChildrenResponse): ChildrenEntity {
        return ChildrenEntity(
            data = getChildrenDataToDomain(data.data)
        )
    }

    override suspend fun getChildrenDataToDomain(data: ChildrenDataResponse): ChildrenDataEntity {
        db.memesDao().insertAll(
            MemeLocalEntity(
                id = 0,
                title = data.title,
                url = data.url,
                score = data.score,
                numComments = data.numComments
            )
        )

        return ChildrenDataEntity(
            title = data.title,
            url = data.url,
            score = data.score,
            numComments = data.numComments
        )
    }

    override suspend fun getListMemesLocalDataToDomain(data: MutableList<MemeLocalEntity>): MutableList<ChildrenEntity> {
        val listChildren: MutableList<ChildrenEntity> = mutableListOf()
        data.forEach {
            listChildren.add(getChildrenLocalDataToDomain(it))
        }
        return listChildren
    }

    override suspend fun getChildrenLocalDataToDomain(localEntity: MemeLocalEntity): ChildrenEntity {
        return ChildrenEntity(
            data = getMemesLocalDataToDomain(localEntity)
        )
    }

    override suspend fun getMemesLocalDataToDomain(data: MemeLocalEntity): ChildrenDataEntity {
        return ChildrenDataEntity(
            title = data.title,
            url = data.url,
            score = data.score,
            numComments = data.numComments
        )
    }

    override suspend fun getMemesSearchDataToDomain(data: MemeResponse): MemeEntity {
        val listChildren: MutableList<ChildrenResponse> = arrayListOf()
        data.children.forEach { children ->
            if (children.data.linkFlairText == "Shitposting" && children.data.postHint == "image") {
                listChildren.add(children)
            }
        }

        return MemeEntity(
            children = listChildren.map {
                getChildrenSearchDataToDomain(it)
            }.toMutableList()
        )
    }

    override suspend fun getChildrenSearchDataToDomain(listChildren: ChildrenResponse): ChildrenEntity {
        return ChildrenEntity(
            data = getChildrenDataSearchDataToDomain(listChildren.data)
        )
    }

    override suspend fun getChildrenDataSearchDataToDomain(listChildrenData: ChildrenDataResponse): ChildrenDataEntity {
        return ChildrenDataEntity(
            title = listChildrenData.title,
            url = listChildrenData.url,
            score = listChildrenData.score,
            numComments = listChildrenData.numComments
        )
    }
}
