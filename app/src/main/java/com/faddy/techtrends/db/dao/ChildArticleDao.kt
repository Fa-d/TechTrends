package com.faddy.techtrends.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faddy.techtrends.models.newModels.ChildArticleModel

@Dao
interface ChildArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(characters: List<ChildArticleModel>)

    @Query("SELECT * FROM child_article_model")
    suspend fun getAllServers(): List<ChildArticleModel>

    @Query("DELETE FROM child_article_model")
    suspend fun clearAllServerList(): Int
}