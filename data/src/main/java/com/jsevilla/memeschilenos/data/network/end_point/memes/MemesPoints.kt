package com.jsevilla.memeschilenos.data.network.end_point.memes

import com.jsevilla.memeschilenos.data.network.response.BaseResponse
import com.jsevilla.memeschilenos.data.network.response.MemeResponse
import com.jsevilla.memeschilenos.domain.entity.Either
import com.jsevilla.memeschilenos.domain.entity.Failure

interface MemesPoints {
    suspend fun getListMemes(): Either<Failure, BaseResponse<MemeResponse>>
    suspend fun getListSearchMemes(data: String): Either<Failure, BaseResponse<MemeResponse>>
}
