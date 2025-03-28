package dev.experimental.techtrends.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.experimental.techtrends.models.CategoryModel
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {
    @Query("SELECT * FROM category_table")
     fun getAllCategoriesDB(): Flow<List<CategoryModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(allData: List<CategoryModel>)

    @Query("DELETE  FROM category_table")
    suspend fun deleteAllCategories(): Int

    @Query("UPDATE category_table SET selectedByUser = CASE WHEN LENGTH(selectedByUser) > 0 THEN '' ELSE :selectedByUser END WHERE id = :id")
    suspend fun setSelectedCategoryByUser(id: Int, selectedByUser: String = "user1")

    @Query("UPDATE category_table SET selectedByUser = '' WHERE name = :categoryName")
    suspend fun removeCategoryFromSelected(categoryName: String)

}