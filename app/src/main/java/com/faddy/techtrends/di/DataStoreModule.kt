package com.faddy.techtrends.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.faddy.techtrends.datastore.TtPref
import com.faddy.techtrends.di.coroutine.ApplicationScope
import com.faddy.techtrends.di.coroutine.Dispatcher
import com.faddy.techtrends.di.coroutine.TtDispatchers
import com.faddy.techtrends.pref.TtPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    internal fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(TtDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        userPreferencesSerializer: TtPreferencesSerializer
    ): DataStore<TtPref> = DataStoreFactory.create(
        serializer = userPreferencesSerializer,
        scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
        migrations = listOf()
    ) {
        context.dataStoreFile("user_preferences.pb")
    }
}