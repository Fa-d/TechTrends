package com.faddy.techtrends.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "mother_article")
data class MotherArticle(
    @PrimaryKey @field: SerializedName("companySite") var companySite: String = "",
    @SerializedName("articleTitle") var articleTitle: String = "",
    @SerializedName("innerItems") var innerItems: InnerItems,
    @SerializedName("image") var image: String = "",
    @SerializedName("title") var title: String = ""
)