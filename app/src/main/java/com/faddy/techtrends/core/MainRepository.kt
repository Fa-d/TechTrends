package com.faddy.techtrends.core

import com.faddy.techtrends.api.ApiService
import com.faddy.techtrends.db.MainDatabase
import com.faddy.techtrends.models.newModels.CategoryModel
import com.faddy.techtrends.models.newModels.ChildArticleModel
import com.faddy.techtrends.utils.SessionManager
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val network: ApiService,
    private val sessionManager: SessionManager,
    private val mainDatabase: MainDatabase
) {

    fun getAllCategories() = network.getAllCategories()
    fun getAllChildArticles() = network.getAllChildArticles()
    fun getAllMotherArticles() = network.getAllMotherArticles()

    suspend fun insetCategory(category: List<CategoryModel>) = mainDatabase.CategoryModelDao().insertCategory(category)
    suspend fun getAllCategoriesFromDb() = mainDatabase.CategoryModelDao().getAllServers()
    suspend fun clearAllCategoriesFromDb() = mainDatabase.CategoryModelDao().clearAllServerList()

    suspend fun insetChildArticleCategory(category: List<ChildArticleModel>) = mainDatabase.ChildArticleDao().insertCategory(category)
    suspend fun getAllChildArticleCategoryFromDb() = mainDatabase.ChildArticleDao().getAllServers()
    suspend fun clearAllChildArticleCategoryFromDb() = mainDatabase.ChildArticleDao().clearAllServerList()


}