package com.faddy.techtrends.db.dao

import com.faddy.techtrends.db.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesCategoryModelDao(database: MainDatabase): CategoryModelDao =
        database.CategoryModelDao()
}