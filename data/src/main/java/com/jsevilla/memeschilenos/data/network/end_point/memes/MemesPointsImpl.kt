package com.jsevilla.memeschilenos.data.network.end_point.memes

import com.jsevilla.memeschilenos.data.network.end_point.EndPoints
import com.jsevilla.memeschilenos.data.network.remote.NetworkHandler
import com.jsevilla.memeschilenos.data.network.response.BaseResponse
import com.jsevilla.memeschilenos.data.network.response.MemeResponse
import com.jsevilla.memeschilenos.domain.entity.Either
import com.jsevilla.memeschilenos.domain.entity.Failure
import retrofit2.Retrofit

class MemesPointsImpl(
    private val networkHandler: NetworkHandler,
    retrofit: Retrofit
) : MemesPoints {
    private val apiRest: EndPoints = retrofit.create(EndPoints::class.java)

    override suspend fun getListMemes(): Either<Failure, BaseResponse<MemeResponse>> =
        networkHandler.callService {
            apiRest.getListMemes(limit = 100)
        }

    override suspend fun getListSearchMemes(data: String): Either<Failure, BaseResponse<MemeResponse>> =
        networkHandler.callService {
            apiRest.getListSearchMemes(data = data, limit = 100)
        }
}
