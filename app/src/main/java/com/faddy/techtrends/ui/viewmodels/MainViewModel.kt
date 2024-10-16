package com.faddy.techtrends.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faddy.techtrends.core.MainRepository
import com.faddy.techtrends.models.FeedChildItem
import com.faddy.techtrends.models.FeedItem
import com.faddy.techtrends.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository, private val sessionManager: SessionManager
) : ViewModel() {
    var feedItemList = MutableLiveData<List<FeedItem>>()

    fun getAllFeedsData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val tempFeedItemList = mainRepository.getAllFeeds()
                feedItemList.postValue(tempFeedItemList)
            }
        }
    }

    suspend fun getFeedByCategory(category: String): List<FeedItem> {
        return mainRepository.getFeedsByCategory(category)
    }


    suspend fun getAllFeedChildByCategory(category: String): List<FeedChildItem> {
        return mainRepository.getAllFeedChildByCategory(category)
    }
}