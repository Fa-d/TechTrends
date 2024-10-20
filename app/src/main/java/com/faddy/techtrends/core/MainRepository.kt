package com.faddy.techtrends.core

import com.faddy.techtrends.api.ApiService
import com.faddy.techtrends.db.MainDatabase
import com.faddy.techtrends.models.FeedChildItem
import com.faddy.techtrends.models.newModels.CategoryModel
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val network: ApiService,
    private val mainDatabase: MainDatabase
) {

    suspend fun getAllCategoriesAPI() = network.getAllCategoriesAPI()
    suspend fun getAllFeedChildByCategory(category: String) = network.getAllFeedChildByCategory(category)
    suspend fun getAllFeeds() = network.getAllFeeds()

    fun getAllCategoriesDB() = mainDatabase.categoryDao().getAllCategoriesDB()
    suspend fun setSelectedCategoryByUser(id: Int) =
        mainDatabase.categoryDao().setSelectedCategoryByUser(id, "user1")
    suspend fun insertAllCategories(allData: List<CategoryModel>) =
        mainDatabase.categoryDao().insertCategories(allData)

    suspend fun deleteAllCategories() = mainDatabase.categoryDao().deleteAllCategories()
    suspend fun insertAllFeedChildItem(feedChildList: List<FeedChildItem>) =
        mainDatabase.feedChildDao().insertFeedChild(feedChildList)

}