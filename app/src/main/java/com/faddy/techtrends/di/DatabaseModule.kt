package com.faddy.techtrends.di

import android.content.Context
import androidx.room.Room
import com.faddy.techtrends.db.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): MainDatabase = Room.databaseBuilder(
        context,
        MainDatabase::class.java,
        "tech_trends_database",
    ).build()
}
