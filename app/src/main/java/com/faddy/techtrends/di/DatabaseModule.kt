package com.faddy.techtrends.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
    )
    //addMigrations(MIGRATION_2_3).addMigrations(MIGRATION_3_4)
        .build()
}

/*

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE category_table ADD COLUMN selectedByUser TEXT DEFAULT '' NOT NULL")
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `feed_child_table` (\n" +
                    "    `id` INTEGER NOT NULL,\n" +
                    "    `category_id` INTEGER NOT NULL DEFAULT 0,\n" +
                    "    `category_name` TEXT NOT NULL DEFAULT '',\n" +
                    "    `company_description` TEXT NOT NULL DEFAULT '',\n" +
                    "    `company_feed_url` TEXT NOT NULL DEFAULT '',\n" +
                    "    `company_logo_url` TEXT NOT NULL DEFAULT '',\n" +
                    "    `company_name` TEXT NOT NULL DEFAULT '',\n" +
                    "    `company_site` TEXT NOT NULL DEFAULT '',\n" +
                    "    `date_posted` TEXT NOT NULL DEFAULT '',\n" +
                    "    `feed_article_url` TEXT NOT NULL DEFAULT '',\n" +
                    "    `feed_author` TEXT NOT NULL DEFAULT '',\n" +
                    "    `feed_content` TEXT,\n" +
                    "    `feed_image` TEXT NOT NULL DEFAULT '',\n" +
                    "    `feed_title` TEXT NOT NULL DEFAULT '',\n" +
                    "    PRIMARY KEY(`id`)\n" +
                    ");"
        )
    }
}*/
