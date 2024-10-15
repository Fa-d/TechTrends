package com.faddy.techtrends

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.MutableStateFlow

@HiltAndroidApp
class MainApp : Application() {

    val isLoadingComplete = MutableStateFlow(false)

}