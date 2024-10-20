package com.faddy.techtrends.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faddy.techtrends.models.newModels.CategoryModel


@Dao
interface CategoryDao {
    @Query("SELECT * FROM category_table")
    suspend fun getAllCategoriesDB(): List<CategoryModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(allData: List<CategoryModel>)

    @Query("DELETE  FROM category_table")
    suspend fun deleteAllCategories(): Int
}