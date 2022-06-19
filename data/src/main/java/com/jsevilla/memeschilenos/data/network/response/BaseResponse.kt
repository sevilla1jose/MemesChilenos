package com.jsevilla.memeschilenos.data.network.response

open class BaseResponse<T>(
    var status: Int,
    val message: String,
    var data: T?
)