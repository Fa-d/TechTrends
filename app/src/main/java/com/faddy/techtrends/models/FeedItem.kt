package com.faddy.techtrends.models


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedItem(
    @SerializedName("feed_topic") var feedTopic: String = "",
    @SerializedName("feed_url") var feedUrl: String? = "",
    @SerializedName("id") var id: Int = 0,
    @SerializedName("link") var link: String = "",
    @SerializedName("title") var title: String = ""
)