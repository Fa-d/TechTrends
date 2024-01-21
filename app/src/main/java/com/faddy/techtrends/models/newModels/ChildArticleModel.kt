package com.faddy.techtrends.models.newModels


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "child_article_model")
data class ChildArticleModel(
    @PrimaryKey @field:SerializedName("tableKey") var tableKey: String = "",
    @SerializedName("creator") var creator: String = "",
    @SerializedName("guid") var guid: String = "",
    @SerializedName("isoDate") var isoDate: String = "",
    @SerializedName("link") var link: String = "",
   // @SerializedName("categories") var categories: List<String> = listOf(),
    @SerializedName("pubDate") var pubDate: String = "",
    @SerializedName("title") var title: String = ""
)