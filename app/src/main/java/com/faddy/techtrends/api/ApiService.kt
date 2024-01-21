package com.faddy.techtrends.api;

import com.faddy.techtrends.models.MotherArticle
import com.faddy.techtrends.models.newModels.ChildArticleModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("tableQuery")
    fun getAllTableContents(): Call<List<MotherArticle>>

    @GET("getAllCategories")
    fun getAllCategories(): Call<List<Map<String, String>>>

    @GET("getAllMotherArticles")
    fun getAllMotherArticles(): Call<List<Map<String, String>>>

    @GET("getAllChildArticles")
    fun getAllChildArticles(): Call<List<ChildArticleModel>>

}