package com.faddy.techtrends.api;

import com.faddy.techtrends.models.FeedChildItem
import com.faddy.techtrends.models.FeedItem
import com.faddy.techtrends.models.newModels.CategoryModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("getCategories")
    suspend fun getAllCategories(): List<CategoryModel>

    @GET("getAllFeeds")
    suspend fun getAllFeeds(): List<FeedItem>

    @GET("getFeedsByCategory")
    suspend fun getFeedsByCategory(@Query("category") category: String): List<FeedItem>

    @GET("getAllFeedChildByCategory")
    suspend fun getAllFeedChildByCategory(@Query("category") category: String): List<FeedChildItem>

}