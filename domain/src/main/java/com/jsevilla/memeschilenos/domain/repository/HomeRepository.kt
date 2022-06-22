package com.jsevilla.memeschilenos.domain.repository

import com.jsevilla.memeschilenos.domain.entity.Either
import com.jsevilla.memeschilenos.domain.entity.Failure
import com.jsevilla.memeschilenos.domain.entity.MemeEntity

interface HomeRepository {
    suspend fun getListMemes(): Either<Failure, MemeEntity>
}
