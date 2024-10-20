package com.faddy.techtrends.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.faddy.techtrends.models.FeedChildItem

@Dao
interface FeedChildDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedChild(feedChildList: List<FeedChildItem>)

}