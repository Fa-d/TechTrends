package dev.experimental.techtrends.di

import android.content.Context
import androidx.room.Room
import dev.experimental.techtrends.db.MainDatabase
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
*/