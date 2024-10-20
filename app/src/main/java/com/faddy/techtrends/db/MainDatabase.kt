package com.faddy.techtrends.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faddy.techtrends.db.dao.CategoryDao
import com.faddy.techtrends.db.dao.ChildArticleDao
import com.faddy.techtrends.db.dao.FeedChildDao
import com.faddy.techtrends.models.FeedChildItem
import com.faddy.techtrends.models.InnerItems
import com.faddy.techtrends.models.newModels.CategoryModel
import com.faddy.techtrends.models.newModels.ChildArticleModel

@Database(
    entities = [InnerItems::class, ChildArticleModel::class, CategoryModel::class, FeedChildItem::class],
    version = 1,
    exportSchema = true
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun ChildArticleDao(): ChildArticleDao
    abstract fun categoryDao(): CategoryDao
    abstract fun feedChildDao(): FeedChildDao

}
