package com.faddy.techtrends.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faddy.techtrends.models.FeedItem

@Dao
interface FeedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeeds(feedList: List<FeedItem>)

    @Query("SELECT * FROM feed_table WHERE categoryName = :category")
    suspend fun getAllFeedItemByCategoryDB(category: String): List<FeedItem>

    @Query("SELECT * FROM feed_table")
    suspend fun getAllFeedItem(): List<FeedItem>

    @Query("UPDATE feed_table SET isFav = :userName WHERE id = :id")
    suspend fun setCompanyFav(id: Int, userName: String = "user1")

    @Query("UPDATE feed_table SET isSavedForLater = :userName WHERE id = :id")
    suspend fun setArticleLater(id: Int, userName: String = "user1")

}