package com.faddy.techtrends.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faddy.techtrends.db.dao.CategoryDao
import com.faddy.techtrends.db.dao.FeedDao
import com.faddy.techtrends.models.FeedItem
import com.faddy.techtrends.models.CategoryModel

@Database(
    entities = [CategoryModel::class, FeedItem::class],
    version = 1,
    exportSchema = true
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun feedDao(): FeedDao

}
