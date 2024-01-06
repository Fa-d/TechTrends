package com.faddy.techtrends.core

import com.faddy.techtrends.api.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val network: ApiService
) {

    fun getArticleData() = network.getAllTableContents()

}