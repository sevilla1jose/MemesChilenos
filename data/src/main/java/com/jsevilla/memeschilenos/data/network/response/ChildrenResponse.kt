package com.jsevilla.memeschilenos.data.network.response

import com.google.gson.annotations.SerializedName

class ChildrenResponse(
    @SerializedName("data")
    val data: ChildrenDataResponse
)
