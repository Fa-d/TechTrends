package dev.experimental.techtrends.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dev.experimental.techtrends.datastore.TtPref
import dev.experimental.techtrends.di.coroutine.ApplicationScope
import dev.experimental.techtrends.di.coroutine.Dispatcher
import dev.experimental.techtrends.di.coroutine.TtDispatchers
import dev.experimental.techtrends.pref.TtPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton


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