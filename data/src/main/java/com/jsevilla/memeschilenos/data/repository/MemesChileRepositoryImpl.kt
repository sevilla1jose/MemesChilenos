package com.jsevilla.memeschilenos.data.repository

import com.jsevilla.memeschilenos.data.local.db.AppDatabase
import com.jsevilla.memeschilenos.data.network.end_point.memes.MemesPoints
import com.jsevilla.memeschilenos.data.network.mapper.memes.MemesMapper
import com.jsevilla.memeschilenos.data.network.utils.ConnectionUtils
import com.jsevilla.memeschilenos.domain.entity.*
import com.jsevilla.memeschilenos.domain.repository.MemesChileRepository

class MemesChileRepositoryImpl(
    private val points: MemesPoints,
    private val mapper: MemesMapper,
    private val db: AppDatabase,
    private val networkUtils: ConnectionUtils
) : MemesChileRepository {
    override suspend fun getListMemes(): Either<Failure, MemeEntity> {
        return if (networkUtils.isNetworkAvailable()) {
            when (val response = points.getListMemes()) {
                is Either.Left -> Either.Left(response.a)
                is Either.Right -> Either.Right(mapper.getMemesDataToDomain(response.b.data!!))
            }
        } else {
            Either.Right(
                MemeEntity(
                    children = mapper.getListMemesLocalDataToDomain(
                        db.memesDao().getAll()
                    )
                )
            )
        }
    }

    override suspend fun getListSearchMemes(data: String): Either<Failure, MemeEntity> {
        return if (networkUtils.isNetworkAvailable()) {
            when (val response = points.getListSearchMemes(data = data)) {
                is Either.Left -> Either.Left(response.a)
                is Either.Right -> Either.Right(mapper.getMemesSearchDataToDomain(response.b.data!!))
            }
        } else {
            Either.Right(
                MemeEntity(
                    children = mapper.getListMemesLocalDataToDomain(
                        db.memesDao().getSearchMeme(data)
                    )
                )
            )
        }
    }
}
