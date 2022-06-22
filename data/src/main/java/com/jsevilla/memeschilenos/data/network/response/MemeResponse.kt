package com.jsevilla.memeschilenos.data.network.response

import com.google.gson.annotations.SerializedName

class MemeResponse(
    @SerializedName("children")
    val children: List<ChildrenResponse>
)
