package dev.experimental.techtrends.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.experimental.techtrends.db.dao.CategoryDao
import dev.experimental.techtrends.db.dao.FeedDao
import dev.experimental.techtrends.models.FeedItem
import dev.experimental.techtrends.models.CategoryModel

@Database(
    entities = [CategoryModel::class, FeedItem::class],
    version = 1,
    exportSchema = true
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun feedDao(): FeedDao

}
