package com.faddy.techtrends.models.newModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "category_model")
data class CategoryModel(
    @PrimaryKey @field:SerializedName("cat_key") var cat_key: String = "",
    @SerializedName("cat_name") var cat_name: String = "",
    var isItemSelected: Boolean = false
)
