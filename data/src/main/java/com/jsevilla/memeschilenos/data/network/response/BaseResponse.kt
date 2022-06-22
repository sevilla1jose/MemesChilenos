package com.jsevilla.memeschilenos.data.network.response

open class BaseResponse<T>(
    val kind: String,
    var data: T?
)
