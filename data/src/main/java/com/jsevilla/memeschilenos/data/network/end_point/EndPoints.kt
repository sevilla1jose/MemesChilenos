package com.jsevilla.memeschilenos.data.network.end_point

import com.jsevilla.memeschilenos.data.network.response.BaseResponse
import com.jsevilla.memeschilenos.data.network.response.MemeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoints {
    @GET("new/.json")
    suspend fun getListMemes(
        @Query("limit") limit: Int
    ): Response<BaseResponse<MemeResponse>>
}
