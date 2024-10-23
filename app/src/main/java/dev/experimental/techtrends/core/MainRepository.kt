package dev.experimental.techtrends.core

import dev.experimental.techtrends.api.ApiService
import dev.experimental.techtrends.db.MainDatabase
import dev.experimental.techtrends.models.CategoryModel
import dev.experimental.techtrends.models.FeedItem
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val network: ApiService,
    private val mainDatabase: MainDatabase
) {

    suspend fun getAllCategoriesAPI() = network.getAllCategoriesAPI()
    suspend fun getAllFeedByCategory(category: String) = network.getAllFeedByCategory(category)

    fun getAllCategoriesDB() = mainDatabase.categoryDao().getAllCategoriesDB()
    suspend fun setSelectedCategoryByUser(id: Int) = mainDatabase.categoryDao().setSelectedCategoryByUser(id )
    suspend fun removeCategoryFromSelected(categoryName: String) = mainDatabase.categoryDao().removeCategoryFromSelected(categoryName )
    suspend fun insertAllCategories(allData: List<CategoryModel>) =
        mainDatabase.categoryDao().insertCategories(allData)

    suspend fun deleteAllCategories() = mainDatabase.categoryDao().deleteAllCategories()
    suspend fun insertAllFeedItem(feedList: List<FeedItem>) =
        mainDatabase.feedDao().insertFeeds(feedList)

    suspend fun getAllFeedItemByCategoryDB(category: String) = mainDatabase.feedDao().getAllFeedItemByCategoryDB(category)
    suspend fun getAllFeedItem() = mainDatabase.feedDao().getAllFeedItem()

    suspend fun setCompanyFav(id: Int) = mainDatabase.feedDao().setCompanyFav(id)
    suspend fun setArticleLater(id: Int) = mainDatabase.feedDao().setArticleLater(id)

}