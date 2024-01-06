package com.faddy.techtrends.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faddy.techtrends.db.dao.InnerItemsDao
import com.faddy.techtrends.models.InnerItems

@Database(entities = [InnerItems::class], version = 1, exportSchema = true)
abstract class MainDatabase : RoomDatabase() {
    abstract fun InnerItemsDao(): InnerItemsDao

}
