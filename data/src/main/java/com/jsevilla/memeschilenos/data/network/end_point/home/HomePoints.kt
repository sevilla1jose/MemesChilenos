package com.jsevilla.memeschilenos.data.network.end_point.home

import com.jsevilla.memeschilenos.data.network.response.BaseResponse
import com.jsevilla.memeschilenos.data.network.response.MemeResponse
import com.jsevilla.memeschilenos.domain.entity.Either
import com.jsevilla.memeschilenos.domain.entity.Failure

interface HomePoints {
    suspend fun getListMemes(): Either<Failure, BaseResponse<MemeResponse>>
}
