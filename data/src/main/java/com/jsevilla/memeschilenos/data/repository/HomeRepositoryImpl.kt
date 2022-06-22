package com.jsevilla.memeschilenos.data.repository

import com.jsevilla.memeschilenos.data.local.db.AppDatabase
import com.jsevilla.memeschilenos.data.network.end_point.home.HomePoints
import com.jsevilla.memeschilenos.data.network.mapper.home.HomeMapper
import com.jsevilla.memeschilenos.data.network.utils.ConnectionUtils
import com.jsevilla.memeschilenos.domain.entity.*
import com.jsevilla.memeschilenos.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val points: HomePoints,
    private val mapper: HomeMapper,
    private val db: AppDatabase,
    private val networkUtils: ConnectionUtils
) : HomeRepository {
    override suspend fun getListMemes(): Either<Failure, MemeEntity> {
        return if (networkUtils.isNetworkAvailable()) {
            when (val response = points.getListMemes()) {
                is Either.Left -> Either.Left(response.a)
                is Either.Right -> Either.Right(mapper.getMemesDataToDomain(response.b.data!!))
            }
        } else {
            Either.Right(MemeEntity(children = mapper.getListMemesLocalDataToDomain(db.memesDao().getAll())))
        }
    }
}
