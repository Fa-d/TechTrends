package com.faddy.techtrends.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.faddy.techtrends.core.MainRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class FeedSyncPerCategoryWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val mainRepository: MainRepository
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        try {
            withContext(Dispatchers.IO) {
                val categoryName = inputData.getString("categoryName") ?: ""
                val response = mainRepository.getAllFeedByCategory(categoryName)
                mainRepository.insertAllFeedItem(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Result.success()
    }
}