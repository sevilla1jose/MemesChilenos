package com.jsevilla.memeschilenos.data.network.end_point.home

import com.jsevilla.memeschilenos.data.network.end_point.EndPoints
import com.jsevilla.memeschilenos.data.network.remote.NetworkHandler
import com.jsevilla.memeschilenos.data.network.response.BaseResponse
import com.jsevilla.memeschilenos.data.network.response.MemeResponse
import com.jsevilla.memeschilenos.domain.entity.Either
import com.jsevilla.memeschilenos.domain.entity.Failure
import retrofit2.Retrofit

class HomePointsImpl(
    private val networkHandler: NetworkHandler,
    retrofit: Retrofit
) : HomePoints {
    private val apiRest: EndPoints = retrofit.create(EndPoints::class.java)

    override suspend fun getListMemes(): Either<Failure, BaseResponse<MemeResponse>> =
        networkHandler.callService {
            apiRest.getListMemes(limit = 100)
        }
}
