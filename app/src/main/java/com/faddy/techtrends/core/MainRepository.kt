package com.faddy.techtrends.core

import com.faddy.techtrends.api.ApiService
import com.faddy.techtrends.db.MainDatabase
import com.faddy.techtrends.models.newModels.ChildArticleModel
import com.faddy.techtrends.utils.SessionManager
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val network: ApiService,
    private val sessionManager: SessionManager,
    private val mainDatabase: MainDatabase
) {

    suspend fun getAllCategories() = network.getAllCategories()
    suspend fun getFeedsByCategory(category: String) = network.getFeedsByCategory(category)
    suspend fun getAllFeeds() = network.getAllFeeds()

    suspend fun insetChildArticleCategory(category: List<ChildArticleModel>) = mainDatabase.ChildArticleDao().insertCategory(category)
    suspend fun getAllChildArticleCategoryFromDb() = mainDatabase.ChildArticleDao().getAllServers()
    suspend fun clearAllChildArticleCategoryFromDb() = mainDatabase.ChildArticleDao().clearAllServerList()


}