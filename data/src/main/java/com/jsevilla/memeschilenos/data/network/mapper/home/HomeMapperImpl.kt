package com.jsevilla.memeschilenos.data.network.mapper.home

import com.jsevilla.memeschilenos.data.local.db.AppDatabase
import com.jsevilla.memeschilenos.data.local.entity.MemeLocalEntity
import com.jsevilla.memeschilenos.data.network.response.ChildrenDataResponse
import com.jsevilla.memeschilenos.data.network.response.ChildrenResponse
import com.jsevilla.memeschilenos.data.network.response.MemeResponse
import com.jsevilla.memeschilenos.domain.entity.ChildrenDataEntity
import com.jsevilla.memeschilenos.domain.entity.ChildrenEntity
import com.jsevilla.memeschilenos.domain.entity.MemeEntity

class HomeMapperImpl(
    private val db: AppDatabase
) : HomeMapper {
    override suspend fun getMemesDataToDomain(data: MemeResponse): MemeEntity {
        db.memesDao().deleteAll()

        val listChildren: ArrayList<ChildrenResponse> = arrayListOf()
        data.children.map { children ->
            if (children.data.linkFlairText == "Shitposting" && children.data.postHint == "image") {
                listChildren.add(children)
            }
        }

        return MemeEntity(
            children = listChildren.map {
                getChildrenDataToDomain(it)
            }
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

    override suspend fun getListMemesLocalDataToDomain(data: List<MemeLocalEntity>): List<ChildrenEntity> {
        val listChildren: ArrayList<ChildrenDataEntity> = arrayListOf()
        data.forEach {
            listChildren.add(getMemesLocalDataToDomain(it))
        }
        return listChildren as List<ChildrenEntity>
    }

    override suspend fun getMemesLocalDataToDomain(data: MemeLocalEntity): ChildrenDataEntity {
        return ChildrenDataEntity(
            title = data.title,
            url = data.url,
            score = data.score,
            numComments = data.numComments
        )
    }
}
