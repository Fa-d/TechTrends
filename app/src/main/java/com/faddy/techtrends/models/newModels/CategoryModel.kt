package com.faddy.techtrends.models.newModels

import com.google.gson.annotations.SerializedName


data class CategoryModel(
    @SerializedName("id") var id: Int = 0, @SerializedName("name") var name: String = ""
)
