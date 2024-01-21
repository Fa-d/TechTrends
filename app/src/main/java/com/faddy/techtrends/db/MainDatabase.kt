package com.faddy.techtrends.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faddy.techtrends.db.dao.CategoryModelDao
import com.faddy.techtrends.db.dao.ChildArticleDao
import com.faddy.techtrends.models.InnerItems
import com.faddy.techtrends.models.newModels.CategoryModel
import com.faddy.techtrends.models.newModels.ChildArticleModel

@Database(
    entities = [InnerItems::class, CategoryModel::class, ChildArticleModel::class],
    version = 1,
    exportSchema = true
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun CategoryModelDao(): CategoryModelDao
    abstract fun ChildArticleDao(): ChildArticleDao

}
