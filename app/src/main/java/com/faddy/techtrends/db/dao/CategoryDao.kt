package com.faddy.techtrends.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faddy.techtrends.models.newModels.CategoryModel
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
    suspend fun setSelectedCategoryByUser(id: Int, selectedByUser: String)

}