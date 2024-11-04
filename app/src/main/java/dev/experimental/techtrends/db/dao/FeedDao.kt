package dev.experimental.techtrends.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.experimental.techtrends.models.FeedItem

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

    @Query("SELECT * FROM feed_table WHERE isSavedForLater = :userName")
    suspend fun getAllSavedArticles(userName: String = "user1"): List<FeedItem>

    @Query("UPDATE feed_table SET isFav = CASE WHEN LENGTH(isFav) > 0 THEN '' ELSE :isFav END WHERE id = :id")
    suspend fun setFeedAsFav(isFav: String, id: Int)

    @Query("UPDATE feed_table SET isAlertOn = CASE WHEN LENGTH(isAlertOn) > 0 THEN '' ELSE :isAlertOn END WHERE id = :id")
    suspend fun setFeedAlert(isAlertOn: String, id: Int)

    @Query("SELECT * FROM feed_table WHERE isFav = :userName")
    suspend fun getAllFavFeeds(userName: String = "user1"): List<FeedItem>

}