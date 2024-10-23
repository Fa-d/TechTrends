package dev.experimental.techtrends.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "category_table")
data class CategoryModel(
    @PrimaryKey @field:SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("selectedByUser") val selectedByUser: String?,

)
