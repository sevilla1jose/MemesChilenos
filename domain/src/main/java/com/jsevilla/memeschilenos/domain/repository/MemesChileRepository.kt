package com.jsevilla.memeschilenos.domain.repository

import com.jsevilla.memeschilenos.domain.entity.Either
import com.jsevilla.memeschilenos.domain.entity.Failure
import com.jsevilla.memeschilenos.domain.entity.MemeEntity

interface MemesChileRepository {
    suspend fun getListMemes(): Either<Failure, MemeEntity>
    suspend fun getListSearchMemes(data: String): Either<Failure, MemeEntity>
}
