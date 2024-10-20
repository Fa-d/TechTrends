package com.faddy.techtrends.core

import com.faddy.techtrends.api.ApiService
import com.faddy.techtrends.db.MainDatabase
import com.faddy.techtrends.models.newModels.CategoryModel
import com.faddy.techtrends.models.newModels.ChildArticleModel
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val network: ApiService,
    private val mainDatabase: MainDatabase
) {

    suspend fun getAllCategoriesAPI() = network.getAllCategoriesAPI()
    suspend fun getFeedsByCategory(category: String) = network.getFeedsByCategory(category)
    suspend fun getAllFeedChildByCategory(category: String) = network.getAllFeedChildByCategory(category)
    suspend fun getAllFeeds() = network.getAllFeeds()

    suspend fun insetChildArticleCategory(category: List<ChildArticleModel>) = mainDatabase.ChildArticleDao().insertCategory(category)
    suspend fun getAllChildArticleCategoryFromDb() = mainDatabase.ChildArticleDao().getAllServers()
    suspend fun clearAllChildArticleCategoryFromDb() = mainDatabase.ChildArticleDao().clearAllServerList()

    suspend fun getAllCategoriesDB() = mainDatabase.categoryDao().getAllCategoriesDB()
    suspend fun insertAllCategories(allData: List<CategoryModel>) =
        mainDatabase.categoryDao().insertCategories(allData)

    suspend fun deleteAllCategories() = mainDatabase.categoryDao().deleteAllCategories()

}