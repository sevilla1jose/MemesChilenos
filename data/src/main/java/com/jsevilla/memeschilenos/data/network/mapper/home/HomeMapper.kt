package com.jsevilla.memeschilenos.data.network.mapper.home

import com.jsevilla.memeschilenos.data.local.entity.MemeLocalEntity
import com.jsevilla.memeschilenos.data.network.response.ChildrenDataResponse
import com.jsevilla.memeschilenos.data.network.response.ChildrenResponse
import com.jsevilla.memeschilenos.data.network.response.MemeResponse
import com.jsevilla.memeschilenos.domain.entity.ChildrenDataEntity
import com.jsevilla.memeschilenos.domain.entity.ChildrenEntity
import com.jsevilla.memeschilenos.domain.entity.MemeEntity

interface HomeMapper {
    suspend fun getMemesDataToDomain(data: MemeResponse): MemeEntity
    suspend fun getChildrenDataToDomain(data: ChildrenResponse): ChildrenEntity
    suspend fun getChildrenDataToDomain(data: ChildrenDataResponse): ChildrenDataEntity

    suspend fun getListMemesLocalDataToDomain(data: List<MemeLocalEntity>): List<ChildrenEntity>
    suspend fun getMemesLocalDataToDomain(data: MemeLocalEntity): ChildrenDataEntity
}
