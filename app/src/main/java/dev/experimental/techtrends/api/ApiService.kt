package dev.experimental.techtrends.api;

import dev.experimental.techtrends.models.CategoryModel
import dev.experimental.techtrends.models.FeedItem
import dev.experimental.techtrends.models.FeedItemByIdReq
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("getCategories")
    suspend fun getAllCategoriesAPI(): List<CategoryModel>

    @GET("getAllFeedChildByCategory")
    suspend fun getAllFeedByCategory(@Query("category") category: String): List<FeedItem>

    @POST("getFeedItemById")
    suspend fun getFeedItemById(@Body id: FeedItemByIdReq): FeedItem?
}