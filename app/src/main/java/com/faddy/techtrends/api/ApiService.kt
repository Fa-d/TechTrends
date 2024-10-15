package com.faddy.techtrends.api;

import com.faddy.techtrends.models.FeedItem
import com.faddy.techtrends.models.newModels.CategoryModel
import com.faddy.techtrends.models.newModels.ChildArticleModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("getCategories")
    suspend fun getAllCategories(): List<CategoryModel>

    @GET("getAllFeeds")
    suspend fun getAllFeeds(): List<FeedItem>

    @GET("getAllChildArticles")
    fun getAllChildArticles(): Call<List<ChildArticleModel>>

}