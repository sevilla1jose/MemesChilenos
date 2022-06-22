package com.jsevilla.memeschilenos.data.network.response

import com.google.gson.annotations.SerializedName

class ChildrenDataResponse(
    @SerializedName("link_flair_text")
    val linkFlairText: String,

    @SerializedName("post_hint")
    val postHint: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("score")
    val score: Int,

    @SerializedName("num_comments")
    val numComments: Int
)
