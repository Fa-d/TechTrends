package dev.experimental.techtrends.ui.test

import androidx.compose.foundation.lazy.LazyListState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LazyListStateModule {

    @Provides
    @Singleton
    @Named("NewsfeedScrollState")
    fun provideNewsfeedScrollState(): LazyListState = LazyListState()

}