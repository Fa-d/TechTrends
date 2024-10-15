package com.faddy.techtrends.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedItem(
    @SerialName("feed_topic") var feedTopic: String = "",
    @SerialName("feed_url") var feedUrl: String? = "",
    @SerialName("id") var id: Int = 0,
    @SerialName("link") var link: String = "",
    @SerialName("title") var title: String = ""
)