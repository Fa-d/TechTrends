package dev.experimental.techtrends.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity(tableName = "feed_table")
data class FeedItem(
    @PrimaryKey(autoGenerate = true) @field:SerializedName("id") val id: Int,
    @SerializedName("category_id") var categoryId: Int = 0,
    @SerializedName("category_name") var categoryName: String = "",
    @SerializedName("company_description") var companyDescription: String = "",
    @SerializedName("company_feed_url") var companyFeedUrl: String = "",
    @SerializedName("company_logo_url") var companyLogoUrl: String = "",
    @SerializedName("company_name") var companyName: String = "",
    @SerializedName("company_site") var companySite: String = "",
    @SerializedName("date_posted") var datePosted: String = "",
    @SerializedName("feed_article_url") var feedArticleUrl: String = "",
    @SerializedName("feed_author") var feedAuthor: String = "",
    @SerializedName("feed_content") var feedContent: String? = "",
    @SerializedName("feed_image") var feedImage: String = "",
    @SerializedName("feed_title") var feedTitle: String = "",
    @SerializedName("isFav") var isFav: String? = "",
    @SerializedName("isSavedForLater") var isSavedForLater: String? = ""
)