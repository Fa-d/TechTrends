package com.faddy.techtrends.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faddy.techtrends.models.newModels.CategoryModel

@Dao
interface CategoryModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(characters: List<CategoryModel>)

    @Query("SELECT * FROM category_model")
    suspend fun getAllServers(): List<CategoryModel>

    @Query("DELETE FROM category_model")
    suspend fun clearAllServerList(): Int
}