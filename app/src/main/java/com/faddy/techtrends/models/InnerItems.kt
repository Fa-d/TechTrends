package com.faddy.techtrends.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Inner_items")
data class InnerItems(
    @PrimaryKey @field:SerializedName("articleUrl") var articleUrl: String = "",
    @SerializedName("pubDate") var pubDate: String = "",
    @SerializedName("summary") var summary: String = "",
    @SerializedName("title") var title: String = ""
)