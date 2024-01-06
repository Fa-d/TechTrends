package com.faddy.techtrends.api;

import com.faddy.techtrends.models.MotherArticle
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("tableQuery")
    fun getAllTableContents(): Call<List<MotherArticle>>

}